# Start with a base image containing Java runtime and Maven
FROM maven:3.9-amazoncorretto-21-debian AS build

# Make source folder
RUN mkdir -p /workspace

WORKDIR /workspace

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the POM
COPY pom.xml .

# Copy the source code
COPY src src

# Builds the application and stores it in /target
RUN ./mvnw clean package

# A new stage so that we won't need maven in the final image
FROM openjdk:21

# Make application folder
RUN mkdir -p /app

WORKDIR /app

# Copy the jar file from the previous stage
COPY --from=build /workspace/target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]