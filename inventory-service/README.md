# Inventory Service

- Purpose: Stock levels, reservations, confirmation, and release handling.
- DB: PostgreSQL
- Events: consumes `order-confirmed`, `order-cancelled`
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl inventory-service
```

## Key Endpoints
```http
GET  /api/inventory/check?productId={id}&quantity={q}
POST /api/inventory/reserve            # Reserve stock for order
POST /api/inventory/release            # Release reservation on cancel/failure
```

## Notes
- Listeners adjust reservations only on order confirmation/cancellation to avoid double deduction.
