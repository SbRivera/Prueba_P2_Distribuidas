FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=target/health-analyzer-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
