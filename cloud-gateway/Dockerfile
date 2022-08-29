FROM openjdk:11

COPY target/cloud-gateway.jar dockerCloudGateway.jar

CMD ["java", "com.gateway.CloudGateway"]

ENTRYPOINT  ["java", "-jar", "dockerCloudGateway.jar" ]

