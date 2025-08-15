# 🏗️ Build stage
FROM maven:3.9.10-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy the entire multi-module project (including parent POM and all services)
COPY . .

# First install the parent POM (no modules, just parent)
RUN mvn clean install -N

# Then build only jwt-service and its dependencies
RUN mvn -pl user-service -am clean package -DskipTests

# 🚀 Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/user-service/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
