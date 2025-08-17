# Recommendation Service

- Purpose: Personalized recommendations from user and order signals.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl recommendation-service
```

## Endpoints
```http
GET /api/recommendations/{userId}
```

## Events
- Consumes: order/cart/product/user events.

## Health
- `GET /actuator/health`
