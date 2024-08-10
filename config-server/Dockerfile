FROM eclipse-temurin:21

LABEL maintainer="thesarfo1@gmail.com"

WORKDIR /app

COPY target/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]