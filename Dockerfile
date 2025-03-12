# Build stage
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Install curl for health check
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

# Copy JAR from build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Start application
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]

# Health check (update port to 8080)
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD curl --fail http://localhost:8080/actuator/health || exit 1