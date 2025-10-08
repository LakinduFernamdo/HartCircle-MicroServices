FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/post-service*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
