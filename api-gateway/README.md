# API Gateway

- Purpose: Single entry point, routing, cross-cutting concerns.
- Tech: Spring Cloud Gateway.
- Depends on: `service-registry/` (Eureka), `config-server/`.
- See also: `../docs/Ecommerce-System-Architecture.md`.

## Run
```bash
mvn spring-boot:run -pl api-gateway
```

## Config
- Profiles: `dev`, `docker`, `prod` (SPRING_PROFILES_ACTIVE)
- Common env: `JWT_SECRET`, Eureka URL, Config Server URL

## Health
- GET `http://localhost:8080/actuator/health`

## Notes
- Routes are configured via application.yml and/or Config Server.
