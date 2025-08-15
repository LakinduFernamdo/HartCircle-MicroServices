# 🏗️ Build stage
FROM maven:3.9.10-eclipse-temurin-21 AS builder

WORKDIR /app

# Copy the entire multi-module project (including parent POM and all services)
COPY . .

# First install the parent POM (no modules, just parent)
RUN mvn clean install -N

# Then build only jwt-service and its dependencies
RUN mvn -pl post-service -am clean package -DskipTests

# 🚀 Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/post-service/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
