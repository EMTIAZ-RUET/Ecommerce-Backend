# Delivery Service

The Delivery Service is responsible for managing the delivery of orders to customers. It tracks deliveries, assigns couriers, and provides real-time location updates.

## Features

- Create and manage deliveries for orders
- Assign couriers to deliveries
- Track delivery status and location in real-time
- Find nearby couriers for efficient delivery assignment
- Spatial data support for location-based operations

## Database Schema

The service uses PostgreSQL with PostGIS extension for spatial data support. The main entities are:

### Delivery
- Tracks order deliveries with status, locations, and timestamps
- Uses spatial data types for pickup, delivery, and current locations
- Maintains relationship with couriers and delivery addresses

### Courier
- Manages courier information, capacity, and current location
- Tracks active status and current delivery load
- Uses spatial data for location tracking

### DeliveryAddress
- Stores detailed address information for deliveries
- Linked to deliveries in a one-to-one relationship

## Event-Driven Communication

The Delivery Service participates in event-driven communication with other microservices:

### Consumes Events
- `order-created` - From Order Service to create new deliveries

### Publishes Events
- `delivery-events` - Contains various delivery events:
  - `DELIVERY_CREATED` - When a new delivery is created
  - `DELIVERY_STATUS_CHANGED` - When delivery status changes (assigned, picked up, delivered, etc.)
  - `DELIVERY_LOCATION_UPDATED` - When delivery location is updated

## API Endpoints

### Delivery Endpoints
- `GET /api/deliveries/{trackingNumber}` - Get delivery by tracking number
- `GET /api/deliveries/customer/{customerId}` - Get deliveries by customer ID
- `GET /api/deliveries/order/{orderId}` - Get deliveries by order ID
- `GET /api/deliveries/courier/{courierId}` - Get deliveries by courier ID
- `GET /api/deliveries/status/{status}` - Get deliveries by status
- `GET /api/deliveries/nearby` - Get deliveries near a location
- `PUT /api/deliveries/{trackingNumber}/assign/{courierId}` - Assign courier to delivery
- `PUT /api/deliveries/{trackingNumber}/status` - Update delivery status
- `PUT /api/deliveries/{trackingNumber}/location` - Update delivery current location

### Courier Endpoints
- `GET /api/couriers` - Get all couriers
- `GET /api/couriers/{id}` - Get courier by ID
- `GET /api/couriers/active` - Get active couriers
- `GET /api/couriers/available` - Get available couriers
- `GET /api/couriers/nearby` - Get couriers near a location
- `POST /api/couriers` - Create a new courier
- `PUT /api/couriers/{id}` - Update courier information
- `PUT /api/couriers/{id}/location` - Update courier location
- `PUT /api/couriers/{id}/toggle-active` - Toggle courier active status
- `DELETE /api/couriers/{id}` - Delete a courier