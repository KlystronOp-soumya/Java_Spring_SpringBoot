FROM openjdk:11
ARG JAR_FILE=target/ELKDemo-0.0.1-SNAPSHOT.war
ADD ${JAR_FILE} ELKDemo.war
ENTRYPOINT ["java","-jar","/ELKDemo.war"]