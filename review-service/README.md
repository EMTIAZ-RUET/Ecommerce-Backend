# Review Service

- Purpose: Manage product reviews and ratings; moderation.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl review-service
```

## Key Endpoints
```http
GET    /api/reviews/product/{productId}
POST   /api/reviews
PUT    /api/reviews/{id}
DELETE /api/reviews/{id}
```

## Events
- Produces: `review-created`, `review-updated`

## Health
- `GET /actuator/health`
