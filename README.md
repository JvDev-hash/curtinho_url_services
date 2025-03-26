# Curtinho App Service

A Spring Boot-based URL shortening service with QR code generation capabilities.

## Description

Curtinho App Service is a backend service that provides URL shortening functionality along with QR code generation features. Built with Spring Boot 3.2.4, it offers a secure and efficient way to manage shortened URLs.

## Features

- URL shortening
- QR code generation
- Spring Security integration
- H2 in-memory database
- JPA for data persistence
- Endpoint caching with Redis

## Prerequisites

- Java 17 or higher
- Maven
- Redis stack server

## Tech Stack

- Spring Boot 3.2.4
- Spring Security
- Spring Data JPA
- H2 Database
- ZXing (QR Code generation)
- Lombok
- Redis

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

### URL Management

#### Create Short URL
- **Endpoint**: `POST /url/gen`
- **Description**: Creates a shortened URL from a long URL
- **Authentication**: Requires API key in Authorization header
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
- **Endpoint**: `GET /url/pop/{shortUri}`
- **Description**: Redirects to the original URL if not expired
- **Parameters**: 
  - `shortUri`: The shortened URL identifier
- **Response**: Redirects to the original URL or returns 404 if not found/expired

#### Delete Short URL
- **Endpoint**: `DELETE /url/del`
- **Description**: Deletes a shortened URL
- **Request Body**:
```json
{
    "shortenUri": "short-url-to-delete"
}
```

#### List URLs
- **Endpoint**: `GET /url/list`
- **Description**: Lists all URLs for the given API key
- **Authentication**: Requires API key in Authorization header
- **Response**: List of URL entities

#### Find URI by Original URL
- **Endpoint**: `GET /url/findUri`
- **Description**: Finds shortened URI by original URL
- **Authentication**: Requires API key in Authorization header
- **Request Body**: Original URL as string
- **Response**:
```json
{
    "shortenUri": "found-short-url"
}
```

### QR Code Generation

#### Generate QR Code
- **Endpoint**: `POST /qr/gen`
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

#### Create API Key
- **Endpoint**: `POST /apiKey/gen`
- **Description**: Generates an API key for a user
- **Request Body**:
```json
{
    "username": "user123",
    "environment": "environment-name"
}
```
- **Response**: 
```json
{
    "hashKey": "generated-api-key"
}
```

>**OBS: Sometime in the future, this will be updated to a Swagger doc page**

## Security

The API endpoints are secured as follows:
- Most endpoints require an API key in the Authorization header
- The URL redirection endpoint (`GET /url/pop/{shortUri}`) is publicly accessible
- API key generation endpoint (`POST /apiKey/gen`) is publicly accessible

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 
