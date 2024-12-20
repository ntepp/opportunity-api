# Use a slim Java 17 image
FROM amazoncorretto:17-alpine

# Copy only the JAR file from the build artifact
COPY target/*.jar opportunity-service.jar

# Expose container port (adjust if necessary)
EXPOSE 8080

# Set the command to run your application
ENTRYPOINT ["java", "-jar", "opportunity-service.jar"]