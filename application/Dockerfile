FROM eclipse-temurin:17-jdk-jammy

WORKDIR /usr/src/application
COPY application .
ENV POSTGRES_USER=${POSTGRES_USER}
ENV POSTGRES_PASSWORD=${POSTGRES_PASSWORD}

RUN chmod +x gradlew
RUN ./gradlew testClasses

ENTRYPOINT ["./gradlew", "bootRun"]
