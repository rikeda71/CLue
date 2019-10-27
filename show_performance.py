import subprocess
import os
from dotenv import load_dotenv


load_dotenv('.env')
USER = os.environ.get('DB_ROOT_USER')
PASS = os.environ.get('DB_ROOT_PASSWORD')

subprocess.run(
    [
        'mlflow', 'ui', '--backend-store-uri',
        f'mysql+pymysql://{USER}:{PASS}@localhost:33036/mlflow_log',
        '--default-artifact-root', 'mlruns'
    ]
)
