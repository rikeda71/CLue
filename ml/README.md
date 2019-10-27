# Paper Classification API

## Requirements

- python3
  - mlflow
  - python-dotenv
- docker

## Usage

### Build docker image

#### development image

```sh
# user directory is /path/to/your/CLue/
docker build -t clue-mlimage -f docker/ml/dev/Dockerfile . # wait a minute
```

### Run training script

```sh
# user directory is /path/to/your/CLue/
docker-compose up -d db  # Experimental Data (research papers) must be prepared for db container
python3 experiment.py ... (add options)
```

### Confirm performances of paper classification with mlflow

```sh
# user directory is /path/to/your/CLue/
python3 show_performance.py
# After execute a script, show localhost:5000 with web browser
```

## Avairable Machine Learning Model

- xgboost
- lightgbm
- Others implemented by scikit-learn (SVM, LogisticRegression, etc...)

## Note

mlflow does not support setting options of `docker run` command.

Therefore, we prepared `experiment.py`.

Ordinarily, we use `mlflow run` command and `MLproject` file to train paper classification model.

https://github.com/mlflow/mlflow/pull/1808
