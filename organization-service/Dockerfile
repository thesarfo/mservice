FROM eclipse-temurin:21

LABEL maintainer="thesarfo1@gmail.com"

WORKDIR /app

COPY target/organization-service-0.0.1-SNAPSHOT.jar /app/organization-service.jar

ENTRYPOINT ["java", "-jar", "organization-service.jar"]