FROM eclipse-temurin:17-jdk-jammy
WORKDIR /usr/src/application
COPY ../../application .
RUN chmod +x gradlew
ENTRYPOINT ["sh", "-c", "./gradlew bootRun -Pargs=--csrf.token=${CSRF_TOKEN}"]
