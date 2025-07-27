# Fluente Backend

A Spring Boot backend application for the Fluente language learning portfolio project, featuring JWT authentication and vocabulary management.

## Features

- 🔐 **JWT Authentication**: Secure user registration and login with JWT tokens
- 📚 **Vocabulary Management**: Add, retrieve, and manage vocabulary words for each user
- 🛡️ **Spring Security**: Protected endpoints with role-based access control
- 🗄️ **MySQL Database**: Persistent data storage with JPA/Hibernate
- 🔄 **CORS Support**: Cross-origin resource sharing enabled for frontend integration

## Tech Stack

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Security 6.5.1**
- **Spring Data JPA**
- **MySQL Database**
- **JWT (JSON Web Tokens)**
- **Maven**
- **Lombok**

## Project Structure

```
src/main/java/com/fluente/
├── config/                 # Configuration classes
├── controller/            # REST API controllers
│   ├── AuthController.java
│   ├── TestController.java
│   └── VocabularyController.java
├── dto/                   # Data Transfer Objects
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   ├── RegisterRequest.java
│   ├── VocabularyRequest.java
│   └── VocabularyResponse.java
├── model/                 # Entity models
│   ├── Role.java
│   ├── User.java
│   └── Vocabulary.java
├── repository/            # Data access layer
│   ├── UserRepository.java
│   └── VocabularyRepository.java
├── security/              # Security configuration
│   ├── JwtAuthenticationFilter.java
│   └── SecurityConfig.java
└── service/               # Business logic
    ├── AuthenticationService.java
    ├── CustomUserDetailsService.java
    ├── JwtService.java
    ├── UserService.java
    └── VocabularyService.java
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate user and get JWT token

### Vocabulary Management
- `GET /api/vocabulary` - Get all vocabulary words for authenticated user
- `POST /api/vocabulary` - Add a new vocabulary word
- `GET /api/vocabulary/test` - Test endpoint

## Setup Instructions

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Database Setup
1. Create a MySQL database named `fluente_db`
2. Update `src/main/resources/application.properties` with your database credentials

### Running the Application
1. Clone the repository
2. Navigate to the project directory
3. Run: `mvn spring-boot:run`
4. The application will start on `http://localhost:8080`

### Environment Variables
Update `application.properties` with your database configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fluente_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## API Usage Examples

### Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

### Add Vocabulary Word
```bash
curl -X POST http://localhost:8080/api/vocabulary \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "word": "bonjour",
    "translation": "hello",
    "pronunciation": "bohn-ZHOOR",
    "context": "Greeting someone in French"
  }'
```

### Get All Vocabulary
```bash
curl -X GET http://localhost:8080/api/vocabulary \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Security Features

- **Password Encryption**: BCrypt password hashing
- **JWT Tokens**: Stateless authentication with 24-hour expiration
- **CORS Configuration**: Configured for frontend integration
- **Protected Endpoints**: Vocabulary endpoints require authentication

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is part of the Fluente portfolio project.

## Contact

For questions or support, please open an issue on GitHub. 