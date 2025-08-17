# Auth Service

- Purpose: Authentication (JWT issuance/validation), authorization helpers.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl auth-service
```

## Key Endpoints
```http
POST /api/auth/login           # Issue JWT
POST /api/auth/refresh         # Refresh token (if supported)
```

## Notes
- Validates credentials and issues JWT used by API Gateway and services.
- Configure `JWT_SECRET`, expiration, and issuer in config.
- Eureka client for discovery.
