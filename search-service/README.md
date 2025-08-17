# Search Service

- Purpose: Full-text and faceted product search (Elasticsearch/OpenSearch).
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl search-service
```

## Endpoints
```http
GET /api/search?q={query}&page=0&size=20
```

## Events
- Consumes: `product` change events to update index.

## Health
- `GET /actuator/health`
