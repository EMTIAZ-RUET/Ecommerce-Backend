# Reporting Service

- Purpose: Operational/business reports (scheduled and ad-hoc).
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl reporting-service
```

## Endpoints
```http
GET /api/reports/{reportName}
```

## Health
- `GET /actuator/health`
