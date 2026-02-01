# DockerJava

A simple Java application demonstrating Docker containerization using Maven and multi-stage Docker builds.

## Features

- Simple Java application built with Maven
- Multi-stage Dockerfile for optimized image size
- Alpine-based runtime image for minimal footprint
- Java 11 compatibility

## Prerequisites

- Java 11 or higher (for local build)
- Maven 3.6+ (for local build)
- Docker (for containerized build and run)

## Project Structure

```
DockerJava/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── dockerjava/
│                   └── App.java          # Main application class
├── pom.xml                               # Maven configuration
├── Dockerfile                            # Multi-stage Docker build
├── .dockerignore                         # Docker build exclusions
├── .gitignore                           # Git exclusions
└── README.md                            # This file
```

## Building and Running Locally

### Build with Maven

```bash
mvn clean package
```

### Run Locally

```bash
java -jar target/docker-java-app-1.0-SNAPSHOT.jar
```

## Building and Running with Docker

### Build Docker Image

```bash
docker build -t docker-java-app .
```

### Run Docker Container

```bash
docker run --rm docker-java-app
```

### Run with Arguments

```bash
docker run --rm docker-java-app arg1 arg2 arg3
```

## Docker Image Details

The Dockerfile uses a multi-stage build:

1. **Build Stage**: Uses `maven:3.9-eclipse-temurin-11` to compile and package the application
2. **Runtime Stage**: Uses lightweight `eclipse-temurin:11-jre-alpine` image containing only the JRE

This approach minimizes the final image size by excluding build tools and dependencies from the runtime image.

## Output Example

When you run the application, you should see output similar to:

```
Hello from Docker Java Application!
Java Version: 11.0.x
Operating System: Linux
Application is running successfully in a containerized environment.
```

## License

This is a demonstration project.