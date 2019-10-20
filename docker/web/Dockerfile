FROM openjdk:11-jdk-slim-sid

RUN apk update \
    && apk --no-cache add openjdk11-jre-headless \
    && rm -rf /var/cache/apk/* \
    && mkdir /app

WORKDIR /app
ADD build.gradle .
ADD gradlew .
ADD settings.gradle .
ADD gradle gradle

ENV JAVA_TARGET "clue.jar"
ENTRYPOINT ["sh" "-c" "java $JAVA"]