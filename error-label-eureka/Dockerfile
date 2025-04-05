FROM maven:3.9.6-eclipse-temurin-17 as build
COPY . /sources
WORKDIR /sources
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk as runner
WORKDIR /app
COPY --from=build /sources/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]

#FROM openjdk:17-jdk as runner
#WORKDIR /app
#COPY --from=build /sources/build/libs/* .
#CMD java -jar "eureka-server-0.0.1-SNAPSHOT.jar"