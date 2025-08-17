# Logging Service

- Purpose: Central log aggregation and dashboards.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl logging-service
```

## Notes
- Ships logs to Elasticsearch; view in Kibana.

## Health
- `GET /actuator/health`
