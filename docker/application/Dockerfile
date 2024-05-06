#FROM eclipse-temurin:17-jdk-jammy AS build
#COPY --chown=gradle:gradle ../../application /usr/src/application
#WORKDIR /usr/src/application
#RUN gradle clean build --no-daemon

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /usr/src/application
COPY ../../application .
RUN chmod +x gradlew
ENTRYPOINT ["sh", "-c", "./gradlew bootRun -Pargs=--csrf.token=${CSRF_TOKEN}"]
