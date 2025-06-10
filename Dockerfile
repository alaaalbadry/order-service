# Use JDK 21 base image
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy jar to container
COPY target/demo-order-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8081

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
