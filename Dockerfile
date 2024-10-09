# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/franquicia-0.0.1-SNAPSHOT.jar /app/franquicia-0.0.1-SNAPSHOT.jar

# Expose the application port (if necessary)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "franquicia-0.0.1-SNAPSHOT.jar"]
