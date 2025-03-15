FROM ubuntu
RUN apt-get update && apt-get install openjdk-17-jdk curl vim -y
WORKDIR /opt
ADD target/scpi-invest-plus-notification-*.jar scpi-invest-plus-notification.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/scpi-invest-plus-notification.jar"]
