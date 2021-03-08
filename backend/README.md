# CLue-api

## How to setup heroku

```shell script
heroku apps:create clue-api
heroku addons:add cleardb:ignite --app clue-api
# 下記コマンドでcleardb の URL が出現するのでこれを application.yml の datasource.url にコピーする
heroku config --app clue-api -a clue-api
heroku config:set SPRING_PROFILES_ACTIVE: heroku
```
