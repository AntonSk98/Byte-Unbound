# Use the official OpenJDK 21 image from Docker Hub
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file from the local machine to the container
# Assuming the JAR file is built and located in the target directory (for Maven)
COPY target/byte-unbound-*.jar byte-unbound.jar

# Command to run your Spring Boot JAR file
ENTRYPOINT ["java", "-jar", "/app/byte-unbound.jar"]
