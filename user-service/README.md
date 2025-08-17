# User Service

## Overview
Purpose: Manage user identities, profiles, and existence checks used by other services.

See also: `../docs/Ecommerce-System-Architecture.md`.

## Run
```bash
mvn spring-boot:run -pl user-service
```

## Key Endpoints
```http
POST /api/auth/login                  # Authenticate and get JWT
POST /api/users/register              # Register new user
GET  /api/users/{userId}              # Get user profile
PUT  /api/users/{userId}              # Update user profile
GET  /api/users/{userId}/exists       # Existence check (used by Order Service)
```

## Events
- Produces: `user-registered`, `user-profile-updated`

## Health
- `GET /actuator/health`

## Notes
- DB: PostgreSQL
- Security: JWT-based auth, role-based authorization
- Discovery: Eureka client

## Scalability
Stateless; horizontally scalable behind a load balancer.