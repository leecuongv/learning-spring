FROM maven:3.9.8-jdk-17 AS build
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar"]