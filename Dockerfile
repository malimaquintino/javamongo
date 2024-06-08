#
# Build stage
#
FROM maven:3.9.6-eclipse-temurin-17 AS build
ENV HOME=/usr/app
WORKDIR $HOME
RUN mkdir -p $HOME
ADD . $HOME
RUN  mvn clean package

#
# Package stage
#
FROM openjdk:17-jdk-slim
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE /app/javamongo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT java -jar /app/javamongo-0.0.1-SNAPSHOT.jar