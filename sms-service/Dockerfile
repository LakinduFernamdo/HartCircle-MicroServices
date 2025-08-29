FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/sms-service-1.0.0.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
