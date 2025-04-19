# Stage 1: Build all modules
FROM maven:3.9.6-eclipse-temurin-17 AS builder
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Stage 2: Minimal Java runner
FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE
COPY --from=builder /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]