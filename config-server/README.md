# Config Server

- Purpose: Centralized configuration for all services.
- See also: `../docs/Ecommerce-System-Architecture.md`.

## Run
```bash
mvn spring-boot:run -pl config-server
```

## Notes
- Backed by local filesystem or Git (configure in application.yml).
- Clients set `spring.config.import` to point here.
