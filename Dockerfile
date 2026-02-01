# Dockerfile for Java application
# Note: Build the JAR locally first with 'mvn clean package'

FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

# Copy the pre-built JAR
COPY target/docker-java-app-1.0-SNAPSHOT.jar app.jar

# Expose port (optional, for future use if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
