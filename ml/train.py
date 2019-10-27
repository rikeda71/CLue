from typing import List, Dict, Any
import os
import re

from dotenv import load_dotenv
import MeCab
import numpy as np
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sklearn.linear_model import LogisticRegression
from sklearn.svm import LinearSVC
from sklearn.neural_network import MLPClassifier
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.model_selection import train_test_split
from sklearn.model_selection import GridSearchCV
from sklearn.metrics import (
    classification_report,
    precision_score,
    recall_score,
    f1_score,
    accuracy_score,
)
import mlflow
import mlflow.sklearn

from models import Authors
from models import Papers
from models import PaperWrittenAuthors
from config import get_args
from config import ML_MODELS

load_dotenv('.env')


def show_performance(y_test: List[str], y_pred: List[str]):
    precision = precision_score(y_test, y_pred, average='macro').item()
    recall = recall_score(y_test, y_pred, average='macro').item()
    f1_measure = f1_score(y_test, y_pred, average='macro').item()
    accuracy = accuracy_score(y_test, y_pred).item()
    mlflow.log_metric('precision', precision)
    mlflow.log_metric('recall', recall)
    mlflow.log_metric('f1-measure', f1_measure)
    mlflow.log_metric('accuracy', accuracy)
    print(classification_report(y_test, y_pred))


def LR() -> Dict[str, Any]:
    model = LogisticRegression(
        penalty='l2', multi_class='auto', solver='liblinear'
    )
    param_grid = {
        'C': [10 ** i for i in range(-5, 6)],
        'class_weight': [None, 'balanced']
    }
    mlflow.log_param('model', 'LogisticRegression')
    mlflow.log_param('penalty', 'l2')
    mlflow.log_param('multi_class', 'auto')
    mlflow.log_param('solver', 'liblinear')

    return {'model': model, 'param_grid': param_grid}


def SVM() -> Dict[str, Any]:
    model = LinearSVC(penalty='l2')
    param_grid = {
        'C': [10 ** i for i in range(-5, 6)],
        'class_weight': [None, 'balanced']
    }
    mlflow.log_param('model', 'LinearSVC')
    mlflow.log_param('penalty', 'l2')

    return {'model': model, 'param_grid': param_grid}


def MLP() -> Dict[str, Any]:
    hidden_layers = [(i, ) for i in range(100, 501, 100)]
    hidden_layers += [(i, j) for i in range(100, 501, 100)
                      for j in range(100, 501, 100)]
    param_grid = {
        'activation': ['identity', 'logistic', 'tanh', 'relu'],
        'solver': ['sgd', 'adam'],
        'hidden_layer_sizes': hidden_layers,
        'learning_rate_init': [10 ** i for i in range(-5, 0)],
    }
    model = MLPClassifier()
    mlflow.log_param('model', 'MLPClassifier')

    return {'model': model, 'param_grid': param_grid}


def train(session, ml_model: str, vector_type: str):
    mecab = MeCab.Tagger('-Owakati')
    tail = re.compile('\s*\n*$')
    papers = session.query(Papers).all()
    dataset = [
        {
            'label': paper.label,
            'session': paper.session,
            'title': tail.sub('', mecab.parse(paper.title)),
            'introduction': tail.sub('', mecab.parse(paper.title).replace(' \n', '')),
        }
        for paper in papers if paper.lang == 'japanese' and paper.label != ''
    ]

    # get sentence vectors
    titles = [paper['title'] for paper in dataset]
    if vector_type == 'BoW':
        bow = CountVectorizer(token_pattern='(?u)\\b\\w+\\b')
        vector = bow.fit_transform(titles)

    # get label
    labels = [paper['label'] for paper in dataset]

    x_train, x_test, y_train, y_test = \
        train_test_split(vector, labels,
                         test_size=0.1, random_state=1)

    with mlflow.start_run():
        # get machine learning model
        if ml_model == 'LR':
            ml_settings = LR()
        elif ml_model == 'SVM':
            ml_settings = SVM()
        elif ml_model == 'MLP':
            ml_settings = MLP()
        model_instance = ml_settings['model']
        param_grid = ml_settings['param_grid']
        model = GridSearchCV(
            model_instance, param_grid, cv=10,
            scoring='accuracy', error_score=np.nan
        )

        model.fit(x_train, y_train)
        best = model.best_estimator_
        if ml_model in ML_MODELS:
            mlflow.sklearn.log_model(best, 'model')

        hparams = model.cv_results_['params'][model.best_index_]
        mlflow.log_params(hparams)

        y_pred = best.predict(x_test)
        show_performance(y_test, y_pred)


if __name__ == '__main__':
    base_url = 'mysql+pymysql://{}:{}@db:3306/{}?charset=utf8mb4'
    log_url = base_url.format(
        os.environ.get('DB_ROOT_USER'),
        os.environ.get('DB_ROOT_PASSWORD'),
        'mlflow_log'
    )
    mlflow.set_tracking_uri(log_url)
    model_table_url = base_url.format(
        os.environ.get('DB_ROOT_USER'),
        os.environ.get('DB_ROOT_PASSWORD'),
        'research_paper_db'
    )
    db_engine = create_engine(model_table_url)
    Session = sessionmaker(bind=db_engine)
    session = Session()
    args = get_args()
    train(session, args.model, args.vector)
    session.close()
