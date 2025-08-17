# Cart Service

- Purpose: Manage user shopping carts with validation against products and inventory.
- Store: Redis (typical)
- Events: optional `cart-events`
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl cart-service
```

## Key Endpoints
```http
GET  /api/carts/{userId}
POST /api/carts/{userId}/items        # Add item (validates product + availability)
PUT  /api/carts/{userId}/items/{itemId}
DELETE /api/carts/{userId}/items/{itemId}
DELETE /api/carts/{userId}            # Clear cart
```

## Notes
- Uses Feign to validate product existence and inventory availability before changes.
