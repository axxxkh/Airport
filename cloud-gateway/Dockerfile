FROM openjdk:11

MAINTAINER alxxxkh@gmail.com

COPY target/cloud-gateway.jar dockerCloudGateway.jar

CMD ["java", "com.gateway.CloudGateway"]

ENTRYPOINT  ["java", "-jar", "dockerCloudGateway.jar" ]

