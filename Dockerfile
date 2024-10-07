FROM maven:3.8.4-openjdk-17 AS build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn clean install -DskipTests

FROM openjdk:17
LABEL authors="Bayu_Rahman"
ARG JAR_FILE=*.jar

WORKDIR /app

COPY /target/replication_core-0.0.1-SNAPSHOT.jar /app/replication_core-0.0.1-SNAPSHOT.jar

EXPOSE 2024

ENTRYPOINT ["java", "-jar", "replication_core-0.0.1-SNAPSHOT.jar"]