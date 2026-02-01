# DockerJava

A simple Java application demonstrating Docker containerization using Maven.

## Features

- Simple Java application built with Maven
- Lightweight Dockerfile using Alpine-based JRE image
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

### Build Steps

1. First, build the JAR file with Maven:

```bash
mvn clean package
```

2. Then, build the Docker image:

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

The Dockerfile uses a lightweight `eclipse-temurin:11-jre-alpine` image containing only the Java Runtime Environment (JRE).

The build process is simple:
1. Build the JAR locally using Maven
2. Copy the pre-built JAR into the Docker image
3. Use the Alpine-based JRE image for a minimal footprint

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