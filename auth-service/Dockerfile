FROM openjdk:11

MAINTAINER alxxxkh@gmail.com

COPY target/auth-service.jar dockerAuthApplication.jar

EXPOSE 8084

ENTRYPOINT  ["java", "-jar", "dockerAuthApplication.jar" ]

