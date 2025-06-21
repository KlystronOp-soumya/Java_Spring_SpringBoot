#Stage 1: build the application using maven
FROM maven:3.8.8-eclipse-temurin-11 AS build
RUN echo "Building the app..."
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean compile package -DskipTests
RUN ls -la /app/target

#Stage 2: deploy the service
FROM eclipse-temurin:11-jre-alpine
RUN echo "deploying the app.."
# alternate FROM openjdk:11-jre-slim
WORKDIR /app
VOLUME [ "/app/logs" ]
ARG WAR_FILE=target/*.war
ENV db_oracle_password=system
ENV db_oracle_schema=DUMMY123
ENV db_oracle_userid=DUMMY123
COPY --from=build /app/target/ToDoAppService.war /app/app.war
EXPOSE 8093
ENTRYPOINT ["java" ,"-DtodoServiceLogs=logs" , "-jar" , "/app/app.war"]