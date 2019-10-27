import argparse

ML_MODELS = ['LR', 'SVM', 'MLP', 'GBDT']
VECTORS = ['BoW']


def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('model', choices=ML_MODELS,
                        help='choice machine learning model')
    parser.add_argument('-v', '--vector', choices=VECTORS, default='BoW',
                        help='choice sentence vector')
    args = parser.parse_args()
    return args
