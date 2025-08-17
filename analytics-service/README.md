# Analytics Service

- Purpose: Real-time metrics/dashboards from event streams.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl analytics-service
```

## Events
- Consumes: domain events via Data Pipeline or directly.

## Health
- `GET /actuator/health`
