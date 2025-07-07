FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=target/patient-data-collector-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
