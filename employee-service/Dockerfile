FROM eclipse-temurin:21

LABEL maintainer="thesarfo1@gmail.com"

WORKDIR /app

COPY target/employee-service-0.0.1-SNAPSHOT.jar /app/employee-service.jar

ENTRYPOINT ["java", "-jar", "employee-service.jar"]