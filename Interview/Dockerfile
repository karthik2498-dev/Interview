# Stage 1: Build the application using Gradle
FROM gradle:8.7-jdk17 AS build

WORKDIR /app

# Copy Gradle wrapper and related files
COPY Interview/ /app/

# Make gradlew executable
RUN chmod +x ./gradlew

# Download dependencies (uses Docker cache)
RUN ./gradlew build -x test --no-daemon || true

# Copy the source code
COPY Interview/src /app/src

# Build the application
RUN ./gradlew clean build -x test --no-daemon

# Stage 2: Run the application using a minimal JDK image
FROM openjdk:17-jdk-slim

# Copy the built JAR from stage 1
COPY --from=build /app/build/libs/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 7070
