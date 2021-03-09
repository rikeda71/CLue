# CLue-api

## How to setup heroku

```shell script
./gradlew build 
heroku apps:create clue-api
heroku addons:add cleardb:ignite --app clue-api
heroku addons:create jawsdb:kitefin --name=clue-db --version=8.0 -a clue-api 
heroku config:set SPRING_PROFILES_ACTIVE=heroku
cd ../
heroku comfig:push -a clue-api
cd backend
heroku deploy:jar build/libs/backend.jar -a clue-api
```
