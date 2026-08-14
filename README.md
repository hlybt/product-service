# Product Service

[![CI](https://github.com/hlybt/product-service/actions/workflows/commit-stage.yml/badge.svg)](https://github.com/hlybt/product-service/actions/workflows/commit-stage.yml)

A Spring Boot microservice for managing product data and operations. This service provides RESTful APIs for product management within a shopping/e-commerce system.

## Overview

The Product Service is a Java-based microservice built with Spring Boot that handles all product-related operations. It's part of a larger microservices architecture and provides core functionality[...]

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
│   │   │   └── web/               # REST controllers
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/com/shop/productservice    # End-to-end HTTP tests
│           └── domain/                     # Unit tests 
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

# Or with Gradle installed
gradle build
```

### Run the Service

```bash
# Using Gradle wrapper (Linux/Mac)
./gradlew bootRun

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
- **Error Handling** - Centralized error handling for validation and service errors
- **Testing** - Unit and integration tests included (JUnit 5 + Spring Boot Test)

## Recent changes (since initial README)

- Added product service implementation and REST endpoints for product CRUD.
- Implemented product input validation.
- Added centralized error handling for the service.
- Added application.yml with application configuration; Tomcat-specific configuration has been included.
- Added unit tests for validation and service behavior; some tests were reorganized into com.shop.productservice.domain.
- Added integration tests to validate end-to-end behavior.
- Minor style cleanup: removed unused imports and code formatting tweaks.

## Continuous Integration

This repository includes a GitHub Actions workflow (.github/workflows/commit-stage.yml) that runs the project build and test suite on Ubuntu 26.04 using Temurin Java 25 and Gradle. The workflow is currently configured to be triggered manually (workflow_dispatch).

Badge:
[![CI](https://github.com/hlybt/product-service/actions/workflows/commit-stage.yml/badge.svg)](https://github.com/hlybt/product-service/actions/workflows/commit-stage.yml)

To run the tests locally:
- macOS / Linux: ./gradlew build
- Windows: gradlew.bat build

Notes:
- If you want the workflow to run automatically on push or pull requests, modify the workflow triggers to include `push` and/or `pull_request`.

## API Endpoints

- GET /products — List all products
- GET /products/{id} — Get product by id
- POST /products — Create new product (validated)
- PUT /products/{id} — Update existing product (validated)
- DELETE /products/{id} — Delete product  

## Dependencies

- `spring-boot-starter-webmvc` - Web framework for REST endpoints
- `spring-boot-starter-validation` - Input validation support
- `spring-boot-starter-webmvc-test` - Testing Spring MVC components
- `spring-boot-webtestclient` - End-to-end HTTP tests
- `junit-platform-launcher` - JUnit 5 test execution

## Configuration

The service can be configured through `application.yml`. Common configurations include:

- Server port
- Database connections 
- Application-specific settings

Recent additions include:

- Server and Tomcat configuration (connectors, ports, thread settings) where needed
- Profiles for test vs. production settings
