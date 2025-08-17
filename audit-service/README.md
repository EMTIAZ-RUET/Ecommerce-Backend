# Audit Service

- Purpose: Immutable audit trail of significant actions.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl audit-service
```

## Events
- Consumes: domain events and writes to audit store.

## Health
- `GET /actuator/health`
