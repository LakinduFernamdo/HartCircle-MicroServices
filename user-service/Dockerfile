FROM eclipse-temurin:21-jdk
WORKDIR /app
# Copy the actual JAR and rename to app.jar
COPY target/user-service-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
