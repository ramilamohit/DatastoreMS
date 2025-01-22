# Use an official OpenJDK 17 runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built by Gradle into the container
COPY build/libs/datastore-ms.jar app.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8080

# Command to execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]
