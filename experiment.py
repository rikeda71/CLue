from ml.config import get_args
import os
import subprocess


pwd = os.getcwd()
args = get_args()
subprocess.call(
    [
        'docker', 'run', '--rm', '-it',
        '--network', 'clue_default',
        '-v', f'{pwd}/mlruns:/mlflow/tmp/mlruns',
        '-v' f'{pwd}/ml:/app',
        '-v', f'{pwd}/.env:/app/.env',
        '-e', 'MLFLOW_TRACKING_URI=file:///mlflow/tmp/mlruns',
        'clue-mlimage',
        'python', 'train.py', args.model, '-v', args.vector
    ]
)
