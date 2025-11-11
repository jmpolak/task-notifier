# Use an official OpenJDK image as base
FROM eclipse-temurin:21-jdk-alpine

# Set a working directory in the container
WORKDIR /app

# Install Maven (needed to run mvnw commands)
RUN apk add --no-cache maven bash

# Expose ports
EXPOSE 8080 5005

# Run the Spring Boot application
ENTRYPOINT ["./mvnw", "spring-boot:run", "-Dspring-boot.run.jvmArguments=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"]