FROM openjdk:11

COPY target/eureka-server.jar dockerEurekaServer.jar

EXPOSE 8761

ENTRYPOINT  ["java", "-jar", "dockerEurekaServer.jar" ]

