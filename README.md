# CLue

This application is the CLue to search for CL (Computation and Language) papers

## Usage

### 1. edit `.env`

```txt
DB_USER=`database user name`
DB_PASSWORD=`database user password`
DB_ROOT_USER=`database root user name`
DB_ROOT_PASSWORD=`database root user password`
TZ=Asia/Tokyo(`database time zone. default is 'Asia/Tokyo'`)
```

### 2. docker settings

#### Run development environment

```sh
# user directory is /path/to/your/CLue/
docker-compose build
docker-compose up -d
```

## Other

### Train paper classification model

show [ml/README.md](https://github.com/s14t284/CLue/blob/master/ml/README.md)
