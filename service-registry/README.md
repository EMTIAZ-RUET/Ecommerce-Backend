# Service Registry (Eureka)

- Purpose: Service discovery and registry dashboard.
- See also: `../docs/Ecommerce-System-Architecture.md`.

## Run
```bash
mvn spring-boot:run -pl service-registry
```

## Access
- UI: `http://localhost:8761`

## Health
- GET `http://localhost:8761/actuator/health`
