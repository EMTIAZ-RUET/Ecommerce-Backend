# Monitoring Service

- Purpose: Metrics, alerts, and tracing setup.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl monitoring-service
```

## Notes
- Exposes Prometheus scrape endpoints; Grafana dashboards consumed from infra compose.

## Health
- `GET /actuator/health`
