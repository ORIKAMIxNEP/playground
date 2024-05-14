FROM eclipse-temurin:17-jdk-jammy
WORKDIR /usr/src/application
COPY ../../application .
ENV CSRF_TOKEN=${CSRF_TOKEN}
# RUN chmod +x gradlew
ENTRYPOINT ["sh", "./gradlew", "bootRun"]
