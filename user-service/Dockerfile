FROM openjdk:11

COPY target/user-service.jar dockerUserService.jar

EXPOSE 8087

ENTRYPOINT  ["java", "-jar", "dockerUserService.jar" ]

