FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/bid-service-1.0.0.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "app.jar"]
