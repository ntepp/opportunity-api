# Opportunity API

Manage the creation, updating, and deletion of volunteer opportunities. Retrieve available opportunities.

## CI/CD Status

![Build & Test](https://github.com/ntepp/opportunity-api/actions/workflows/opportunity-service-ci.yml/badge.svg)

## Features

- Create, update, and delete volunteer opportunities.
- Retrieve available opportunities.

## Requirements

- Java 17
- Maven

## Getting Started

### Prerequisites

Ensure you have the following installed:
- Java 17
- Maven

### Build and Run

1. Clone the repository:
   ```
   git clone https://github.com/ntepp/opportunity-api.git
   cd opportunity-api

2. Build the project (skip tests):
```
mvn clean install -DskipTests
```

3. Run the tests:
```
mvn test
```

4. Run the application:
```
mvn spring-boot:run
```

### License
This project is licensed under the MIT License.