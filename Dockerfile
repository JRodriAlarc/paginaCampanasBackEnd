FROM openjdk:17-jdk-alpine


COPY target/ArcaDeLaAlianza-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "/app.jar"]
