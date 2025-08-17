# Payment Service

- Purpose: Process payments and publish payment events.
- DB: PostgreSQL
- Events: produces `payment-events`
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl payment-service
```

## Key Endpoint
```http
POST /api/payments
```

Request example
```json
{
  "orderId": "<orderId>",
  "userId": "user-123",
  "amount": 149.99,
  "paymentMethod": "CREDIT_CARD",
  "paymentMethodId": "pm_abc"
}
```

## Notes
- Called by Order Service during confirmation via Feign client.
- Publishes status to `payment-events` for downstream consumers.
