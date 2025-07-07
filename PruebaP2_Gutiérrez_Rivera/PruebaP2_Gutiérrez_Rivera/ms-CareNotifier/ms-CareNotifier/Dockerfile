FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=target/care-notifier-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
