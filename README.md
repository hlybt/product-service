# Product Service

A Spring Boot microservice for managing product data and operations. This service provides RESTful APIs for product management within a shopping/e-commerce system.

## Overview

The Product Service is a Java-based microservice built with Spring Boot that handles all product-related operations. It's part of a larger microservices architecture and provides core functionality for managing product information, inventory, and related operations.

## Technology Stack

- **Java:** Java 25
- **Framework:** Spring Boot 4.0.5
- **Build Tool:** Gradle
- **Testing:** JUnit 5 with Spring Boot Test
- **Validation:** Spring Validation
- **API:** Spring Web MVC (RESTful APIs)

## Project Structure

```
product-service/
├── src/
│   ├── main/
│   │   ├── java/com/shop/productservice
│   │   │   ├── domain/            # Domain model and business logic 
│   │   │   ├── persistence/       # Data access
│   │   │   ├── web/               # REST controllers
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/shop/productservice
├── build.gradle                    # Gradle build configuration
├── settings.gradle                 # Gradle settings
├── gradlew                         # Gradle wrapper script
└── README.md
```

## Prerequisites

- Java 25 or higher
- Gradle (or use the included Gradle wrapper)

## Getting Started

### Build the Project

```bash
# Using Gradle wrapper (Linux/Mac)
./gradlew build

# Using Gradle wrapper (Windows)
gradlew.bat build

# Or with Gradle installed
gradle build
```

### Run the Service

```bash
# Using Gradle wrapper (Linux/Mac)
./gradlew bootRun

# Using Gradle wrapper (Windows)
gradlew.bat bootRun

# Or with Gradle installed
gradle bootRun

# Or run the built JAR
java -jar build/libs/product-service-0.0.1-SNAPSHOT.jar
```

### Run Tests

```bash
./gradlew test
```

## Features

- **RESTful API Endpoints** - CRUD operations for product management
- **Input Validation** - Request validation using Spring Validation
- **Testing Framework** - Comprehensive test coverage with JUnit 5

## Dependencies

- `spring-boot-starter-webmvc` - Web framework for REST endpoints
- `spring-boot-starter-validation` - Input validation support
- `spring-boot-starter-webmvc-test` - Testing utilities
- `junit-platform-launcher` - JUnit 5 test execution

## Configuration

The service can be configured through `application.yml`. Common configurations include:

- Server port
- Database connections (if applicable)
- Application-specific settings


