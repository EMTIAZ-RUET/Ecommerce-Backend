# Order Service

- Purpose: Manage order lifecycle (PENDING → CONFIRMED → CANCELLED/COMPLETED).
- DB: PostgreSQL
- Events: `order-created`, `order-confirmed`, `order-cancelled`
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl order-service
```

## Key Endpoints
```http
POST /api/orders                     # Create order (sets PENDING, reserves stock)
PUT  /api/orders/{id}/confirm        # Confirm (requires paymentMethodId query param)
PUT  /api/orders/{id}/cancel         # Cancel order
GET  /api/orders/{id}                # Get order by id
```

## Notes
- Confirmation triggers payment via `payment-service` and publishes `order-confirmed`.
- Cancellation publishes `order-cancelled` and releases reservations.
