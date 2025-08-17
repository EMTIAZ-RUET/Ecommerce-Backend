# User Service

## Overview
The User Service is a foundational component of the e-commerce platform, responsible for managing all aspects of user identity and authentication. Its primary responsibilities include user registration, login, password reset, and profile management.

## Key Features
- User registration and authentication
- JWT-based token generation and validation
- User profile management
- Role-based authorization
- Event publishing for user-related events

## API Endpoints
- `POST /api/auth/login` - Authenticate a user and generate JWT token
- `POST /api/users/register` - Register a new user
- `GET /api/users/{userId}` - Get user profile
- `PUT /api/users/{userId}` - Update user profile

## Event Publishing
The User Service publishes the following events to Kafka:
- `user-registered` - When a new user is registered
- `user-profile-updated` - When a user profile is updated

## Database
The User Service uses PostgreSQL as its database for storing user information, providing ACID compliance necessary for handling sensitive user account information.

## Security
The service implements JWT-based authentication and role-based authorization. It securely stores password hashes and manages user sessions.

## Dependencies
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Kafka
- Eureka Client
- JWT

## Configuration
The service is configured via `application.yml` with the following key settings:
- Server port: 8085
- Database connection details
- JWT secret and expiration
- Kafka bootstrap servers

## Scalability
The User Service is designed to be stateless, allowing it to be deployed as multiple instances behind a load balancer to handle a large number of concurrent authentication requests.