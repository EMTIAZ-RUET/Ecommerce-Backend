# Product Catalog Service

## Overview
The Product Catalog Service is the heart of the e-commerce platform's product data management system. It is responsible for storing, retrieving, and managing all information related to products sold on the platform, including names, descriptions, categories, pricing, and stock availability.

## Key Features
- **Flexible Schema**: Uses MongoDB's document model to support dynamic product attributes for different product types
- **RESTful API**: Comprehensive API for product management operations
- **Event-Driven Architecture**: Publishes events to Kafka when products are created, updated, or deleted
- **Scalable Design**: Stateless service that can be deployed across multiple instances

## MongoDB Document Model
The service leverages MongoDB's document-oriented database to provide a flexible schema that can accommodate various product types with different attributes:

- **Core Product Model**: Standard fields like name, description, price
- **Dynamic Attributes**: Custom attributes specific to product types (e.g., CPU, RAM for laptops; size, color for clothing)
- **Product Variants**: Support for product variations with their own attributes and pricing

## API Endpoints

### Product Management
- `GET /api/products` - Retrieve all products (with pagination)
- `GET /api/products/{id}` - Get a specific product by ID
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update an existing product
- `DELETE /api/products/{id}` - Delete a product

## Event Publishing
The service publishes the following events to Kafka:

- `product-events` topic:
  - `PRODUCT_CREATED` - When a new product is created
  - `PRODUCT_UPDATED` - When a product is updated
  - `PRODUCT_DELETED` - When a product is deleted

These events are consumed by other services such as:
- **Search Service**: To update the search index
- **Inventory Service**: To initialize stock levels for new products
- **Recommendation Service**: To update product recommendations

## Database

### MongoDB Collections
- `products` - Main product information
- `product_variants` - Product variants with specific attributes

## Configuration

### Application Properties
```yaml
server:
  port: 8081

spring:
  application:
    name: product-service
  data:
    mongodb:
      uri: mongodb://localhost:27017/product-service
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## Dependencies
- Spring Boot
- Spring Data MongoDB
- Spring Kafka
- Spring Cloud Netflix Eureka Client
- Lombok

## Scalability
The Product Catalog Service is designed to be highly scalable:

- **Stateless Architecture**: Can be deployed across multiple instances
- **MongoDB Sharding**: Supports distributing product data across multiple servers
- **Event-Driven Communication**: Asynchronous communication with other services via Kafka

## Development

### Building the Service
```bash
./mvnw clean package
```

### Running the Service
```bash
./mvnw spring-boot:run
```

### Docker
```bash
docker build -t product-service .
docker run -p 8081:8081 product-service
```