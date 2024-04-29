FROM gradle:8.7.0-jdk17-alpine AS build
COPY --chown=gradle:gradle . /usr/src/application
WORKDIR /usr/src/application
RUN gradle clean build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /usr/src/application
COPY application .

EXPOSE 8080
ENTRYPOINT ["sh", "./gradlew", "bootRun"]
