# Product Catalog Service

Purpose: Manage product catalog (details, categories, variants) and provide existence/detail checks.

See also: `../docs/Ecommerce-System-Architecture.md`.

## Run
```bash
mvn spring-boot:run -pl product-service
```

## Key Endpoints
```http
GET    /api/products                     # List products (with pagination)
GET    /api/products/{id}                # Get product details
GET    /api/products/{id}/exists         # Existence check (used by Cart/Order)
POST   /api/products                     # Create product
PUT    /api/products/{id}                # Update product
DELETE /api/products/{id}                # Delete product
```

## Events
- Produces on `product-events`: `PRODUCT_CREATED`, `PRODUCT_UPDATED`, `PRODUCT_DELETED`
- Typical consumers: Search, Inventory, Recommendation

## Database
- MongoDB (collections: `products`, `categories`, optionally `product_variants`)

## Health
- `GET /actuator/health`

## Notes
- Text and field indexes initialized via `mongo-init.js` (see repo root).
- Discovery: Eureka client