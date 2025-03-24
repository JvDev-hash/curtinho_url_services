# Curtinho App Service

A Spring Boot-based URL shortening service with QR code generation capabilities.

## Description

Curtinho App Service is a backend service that provides URL shortening functionality along with QR code generation features. Built with Spring Boot 3.2.4, it offers a secure and efficient way to manage shortened URLs.

## Features

- URL shortening
- QR code generation
- RESTful API endpoints
- Spring Security integration
- H2 in-memory database
- JPA for data persistence

## Prerequisites

- Java 17 or higher
- Maven

## Tech Stack

- Spring Boot 3.2.4
- Spring Security
- Spring Data JPA
- H2 Database
- ZXing (QR Code generation)
- Lombok

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/curtinho_url_services.git
```

2. Navigate to the project directory:
```bash
cd curtinho_url_services
```

3. Build the project:
```bash
./mvnw clean install
```

4. Run the application:
```bash
./mvnw spring-boot:run
```

## Database Access

The application uses H2 in-memory database for development purposes. You can access the H2 console at:
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:curtinhodb
- Username: sa
- Password: (empty)

Note: The H2 database is in-memory, which means all data will be reset when the application restarts.

## API Endpoints

[Document your API endpoints here]

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 