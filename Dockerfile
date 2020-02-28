FROM maven:3.5.4-jdk-10-slim
COPY ./target/sundevs.war /usr/src/sundevs/
WORKDIR /usr/src/sundevs
EXPOSE 8080
CMD ["java", "-jar", "sundevs.war"]