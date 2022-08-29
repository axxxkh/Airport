FROM openjdk:11

MAINTAINER alxxxkh@gmail.com

COPY target/user-service.jar dockerUserService.jar

EXPOSE 8087

ENTRYPOINT  ["java", "-jar", "dockerUserService.jar" ]

