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
git clone https://github.com/JvDev-hash/curtinho_url_services.git
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
- Username: (any username)
- Password: (any password)

Note: The H2 database is in-memory, which means all data will be reset when the application restarts.

## API Endpoints

### URL Shortening

#### Create Short URL
- **Endpoint**: `POST /s`
- **Description**: Creates a shortened URL from a long URL
- **Request Body**:
```json
{
    "longUrl": "https://example.com/very/long/url",
    "days": 30
}
```
- **Response**: 
```json
{
    "shortenUri": "generated-short-url"
}
```

#### Access Short URL
- **Endpoint**: `GET /p/{shortUri}`
- **Description**: Redirects to the original URL
- **Parameters**: 
  - `shortUri`: The shortened URL identifier
- **Response**: Redirects to the original URL or returns 404 if not found/expired

### QR Code Generation

#### Generate QR Code
- **Endpoint**: `POST /qr`
- **Description**: Generates a QR code for a given URL
- **Request Body**:
```json
{
    "longUrl": "https://example.com/very/long/url"
}
```
- **Response**: 
```json
{
    "shortenUri": "data:image/png;base64,..."
}
```

### API Key Management

#### Create User API Key
- **Endpoint**: `POST /usrKey`
- **Description**: Generates an API key for a user
- **Request Body**:
```json
{
    "username": "user123",
    "appName": "MyApp"
}
```
- **Response**: 
```json
{
    "hashKey": "generated-api-key"
}
```

## Security

The API endpoints are secured with token-based authentication:
- `POST /qr` endpoint require an Authorization header with a valid token
- `GET /p/{shortUri}` and `POST /usrKey` endpoints is publicly accessible
- H2 console is accessible without authentication in development mode

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 