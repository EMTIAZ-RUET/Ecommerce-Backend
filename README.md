# 🛒 Production-Grade E-Commerce Microservices System

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen?style=for-the-badge&logo=spring)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-3.5-red?style=for-the-badge&logo=apache-kafka)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue?style=for-the-badge&logo=docker)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Ready-326CE5?style=for-the-badge&logo=kubernetes)

 **🚀 A comprehensive, production-ready e-commerce platform built with 24 microservices**

 *Featuring event-driven architecture, real-time processing, and enterprise-grade scalability*

  </div>

</details>

 <div align="center">
 
 <svg width="100%" height="140" viewBox="0 0 1200 140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="E-Commerce Microservices Banner">
   <defs>
     <linearGradient id="grad" x1="0%" y1="0%" x2="100%" y2="0%">
       <stop offset="0%" style="stop-color:#2E7D32;stop-opacity:1"/>
       <stop offset="50%" style="stop-color:#1976D2;stop-opacity:1"/>
       <stop offset="100%" style="stop-color:#6A1B9A;stop-opacity:1"/>
     </linearGradient>
     <filter id="shadow" x="-20%" y="-20%" width="140%" height="140%">
       <feDropShadow dx="0" dy="2" stdDeviation="4" flood-color="#000" flood-opacity="0.25"/>
     </filter>
   </defs>
   <rect x="10" y="10" rx="14" ry="14" width="1180" height="120" fill="url(#grad)" filter="url(#shadow)"/>
   <text x="600" y="68" text-anchor="middle" font-family="Segoe UI, Roboto, Arial" font-size="28" font-weight="700" fill="#FFFFFF">Production-Grade E-Commerce Microservices</text>
   <text x="600" y="98" text-anchor="middle" font-family="Segoe UI, Roboto, Arial" font-size="16" fill="#E3F2FD">Java • Spring Boot • Kafka • Docker • Kubernetes</text>
 </svg>
 
 </div>

---

## 📋 Table of Contents

- [🏗️ System Architecture](#️-system-architecture)
- [🎯 Key Features](#-key-features)
- [🔧 Technology Stack](#-technology-stack)
- [📦 Microservices Overview](#-microservices-overview)
- [🔄 Service Interactions](#-service-interactions)
- [🗺️ Visualizations](#️-visualizations)
- [🚀 Quick Start Guide](#-quick-start-guide)
- [📊 Monitoring & Observability](#-monitoring--observability)
- [🔒 Security Features](#-security-features)
- [🧪 Testing Strategy](#-testing-strategy)
- [🐳 Docker & Kubernetes](#-docker--kubernetes)
- [📚 API Documentation](#-api-documentation)
- [🤝 Contributing](#-contributing)
- [📄 License](#-license)

---

## 🏗️ System Architecture

The system consists of **24 microservices** organized into logical layers:

### 🏪 **Core Business Services (7 services)**
- 👤 **User Service** - User management and profiles
- 🔐 **Auth Service** - Authentication and authorization  
- 📦 **Product Service** - Product catalog management
- 📊 **Inventory Service** - Stock management and tracking
- 🛒 **Cart Service** - Shopping cart operations
- 📋 **Order Service** - Order processing and lifecycle
- 💳 **Payment Service** - Payment processing and transactions

### 🚚 **Advanced Services (6 services)**
- 🚛 **Delivery Service** - Shipping and delivery management
- 📧 **Notification Service** - Multi-channel communications
- ⭐ **Review Service** - Product reviews and ratings
- 🔍 **Search Service** - Advanced product search
- 🎯 **Recommendation Service** - AI-powered recommendations
- 📊 **Analytics Service** - Real-time business intelligence

### 🏗️ **Infrastructure Services (11 services)**
- 🚪 **API Gateway** - Single entry point and routing
- 🌐 **Service Registry** - Service discovery (Eureka)
- ⚙️ **Config Server** - Centralized configuration
- 📡 **Monitoring Service** - System health monitoring
- 📋 **Logging Service** - Centralized log management
- 📈 **Reporting Service** - Business reporting
- 📝 **Audit Service** - Compliance and audit trails
- 💾 **Backup Service** - Data backup and recovery
- ⏰ **Scheduler Service** - Job scheduling
- 🌊 **Data Pipeline Service** - Real-time data processing
- 🔧 **Common Service** - Shared utilities and events

---

## 🎯 Key Features

### 🏪 **Complete E-Commerce Platform**
- **Product Management**: Comprehensive catalog with categories, variants, and inventory tracking
- **Shopping Cart**: Redis-powered cart with real-time updates and persistence
- **Order Processing**: Full order lifecycle from creation to fulfillment
- **Payment Integration**: Multi-gateway support with secure transaction processing
- **User Management**: Registration, authentication, profiles, and preferences

### 🔄 **Event-Driven Architecture**
- **Apache Kafka Integration**: Real-time event streaming between services
- **Asynchronous Processing**: Non-blocking operations for better performance
- **Event Sourcing**: Complete audit trail of all business events
- **CQRS Pattern**: Optimized read/write operations

### 🛡️ **Enterprise Security**
- **JWT Authentication**: Stateless token-based security
- **Role-Based Access Control**: Fine-grained permissions
- **API Rate Limiting**: Protection against abuse
- **Data Encryption**: End-to-end security for sensitive data

### 📊 **Advanced Analytics**
- **Real-Time Metrics**: Live dashboards and monitoring
- **Customer Insights**: Behavior analysis and segmentation
- **Business Intelligence**: Comprehensive reporting suite
- **Recommendation Engine**: AI-powered product suggestions

### 🚀 **Cloud-Native Design**
- **Containerized Deployment**: Docker and Kubernetes ready
- **Auto-Scaling**: Horizontal scaling based on demand
- **Service Mesh**: Advanced traffic management
- **Multi-Cloud Support**: Deploy anywhere

---

## 🔧 Technology Stack

<div align="center">

| Category | Technologies |
|----------|-------------|
| **Backend** | ![Java](https://img.shields.io/badge/Java-17-orange?logo=java) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen?logo=spring) ![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2022.0.4-brightgreen?logo=spring) |
| **Databases** | ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue?logo=postgresql) ![MongoDB](https://img.shields.io/badge/MongoDB-6.0-green?logo=mongodb) ![Redis](https://img.shields.io/badge/Redis-7.0-red?logo=redis) |
| **Message Broker** | ![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-3.5-red?logo=apache-kafka) |
| **Search Engine** | ![Elasticsearch](https://img.shields.io/badge/Elasticsearch-8.0-yellow?logo=elasticsearch) |
| **Monitoring** | ![Prometheus](https://img.shields.io/badge/Prometheus-2.40-orange?logo=prometheus) ![Grafana](https://img.shields.io/badge/Grafana-9.0-orange?logo=grafana) |
| **Containerization** | ![Docker](https://img.shields.io/badge/Docker-20.10-blue?logo=docker) ![Kubernetes](https://img.shields.io/badge/Kubernetes-1.25-326CE5?logo=kubernetes) |
| **Build Tools** | ![Maven](https://img.shields.io/badge/Maven-3.9-red?logo=apache-maven) |
| **Testing** | ![JUnit](https://img.shields.io/badge/JUnit-5-green?logo=junit5) ![Mockito](https://img.shields.io/badge/Mockito-4.0-green) ![Testcontainers](https://img.shields.io/badge/Testcontainers-1.19-blue) |

</div>

---

## 📦 Microservices Overview

### 👤 **User Service** `Port: 8081`
**Purpose**: Complete user lifecycle management and authentication

**Key Features**:
- 🔐 User registration and profile management
- 🔑 Password encryption with BCrypt
- 📧 Email verification and password reset
- 👥 User roles and permissions
- 📊 User activity tracking

**Database**: PostgreSQL | **Events**: `UserRegistered`, `UserProfileUpdated`, `UserDeleted`

**API Endpoints**:
```http
POST   /api/users/register          # Register new user
POST   /api/users/login             # User authentication
GET    /api/users/{id}              # Get user profile
PUT    /api/users/{id}              # Update user profile
DELETE /api/users/{id}              # Delete user account
```

**Interactions**:
- **REST (via Gateway)**: Exposed at `http://localhost:8080/api/users/**`
- **Publishes (Kafka)**: `UserRegistered`, `UserProfileUpdated`, `UserDeleted`
- **Consumed by**: `Notification` (welcome, password reset), `Analytics` (user cohorts), `Audit` (immutably logs), `Recommendation` (profile signals)
- **Resilience**: Idempotent registration; transactional outbox for event publication; PII redaction before events

```mermaid
sequenceDiagram
  autonumber
  participant US as User Service
  participant NS as Notification Service
  participant AS as Analytics Service
  participant AU as Audit Service
  participant RS as Recommendation Service
  US->>NS: Publish UserRegistered
  US->>AS: Publish UserProfileUpdated
  US->>AU: Publish UserDeleted
  US->>RS: Publish UserRegistered
```

#### Details
- **Responsibilities**: Manage user identities, profiles, preferences; emit lifecycle events.
- **Primary endpoints**: `/api/users/register`, `/api/users/{id}`, `/api/users/{id}/exists`
- **Data model hints**: `User{id, email, roles[], status, createdAt}`
- **Events purpose**: `UserRegistered` (onboard flows), `UserProfileUpdated` (sync projections), `UserDeleted` (GDPR cleanup)
- **Dependencies**: Calls `Auth` for token flows (login via Gateway), consumed by `Notification`, `Analytics`, `Audit`, `Recommendation`.
- **Auth/Roles**: Self-service endpoints open to `ANON` for register; profile ops require `USER`; admin ops require `ADMIN`.
- **Resilience**: Transactional outbox for events; idempotent registration; PII redaction in events.
- **Observability**: Key metrics `users.created.count`, `users.active.count`; logs redact sensitive fields.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/users/)

##### Example Kafka Payloads
```json
{
  "eventType": "UserRegistered",
  "userId": "b1b2c3d4",
  "email": "alice@example.com",
  "roles": ["USER"],
  "timestamp": "2024-01-01T12:00:00Z",
  "metadata": {"source": "user-service", "version": 1}
}
```

### 🔐 **Auth Service** `Port: 8082`
**Purpose**: Centralized authentication and authorization  

**Key Features**:
- 🎫 JWT token generation and validation
- 🔄 Token refresh mechanism
- 🚪 Single Sign-On (SSO) support
- 🛡️ Role-based access control
- 📱 Multi-factor authentication

**Integration**: Keycloak | **Events**: `UserAuthenticated`, `TokenRefreshed`, `LoginFailed`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/auth/**`
- **Used by**: Gateway filter validates JWT on inbound requests; services verify roles/claims
- **Publishes (Kafka)**: `UserAuthenticated`, `LoginFailed` (optional)
- **Resilience**: Short‑lived tokens, refresh flow, clock skew tolerance

---
```mermaid
sequenceDiagram
  autonumber
  actor CL as Client
  participant GW as API Gateway
  participant AU as Auth Service
  CL->>GW: POST /api/auth/login
  GW->>AU: Validate credentials
  AU-->>GW: 200 JWT({sub, roles, exp})
  GW-->>CL: JWT
  note over GW,AU: GW validates JWT on all requests (roles/claims)
```

#### Details
- **Responsibilities**: Authentication (login, refresh), authorization (RBAC), token introspection.
- **Primary endpoints**: `/api/auth/login`, `/api/auth/refresh`, `/api/auth/introspect`
- **Token model**: JWT with `sub`, `roles`, `exp`, `iat`; refresh token TTL configurable.
- **Events purpose**: `UserAuthenticated` (security analytics), `LoginFailed` (fraud signals).
- **Dependencies**: Integrated at `API Gateway` filter; downstream services trust JWT and enforce roles.
- **Security**: Rate limiting on login; MFA optional; clock-skew tolerant verification.
- **Resilience**: Short‑lived access tokens; stateless; fallback to refresh; lockout after repeated failures.
- **Observability**: Metrics `auth.login.success`, `auth.login.failure`, `jwt.validation.errors`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/auth/)

##### Example Kafka Payloads
```json
{
  "eventType": "UserAuthenticated",
  "userId": "b1b2c3d4",
  "roles": ["USER"],
  "ip": "203.0.113.10",
  "userAgent": "Mozilla/5.0",
  "timestamp": "2024-01-01T12:03:00Z",
  "metadata": {"source": "auth-service", "version": 1}
}
```

---

### 📦 **Product Service** `Port: 8083`
**Purpose**: Product catalog and inventory management

**Key Features**:
- 📋 Product CRUD operations
- 🏷️ Category and tag management
- 🖼️ Image and media handling
- 💰 Pricing and discount management
- 🔍 Product search and filtering

**Database**: MongoDB | **Events**: `ProductCreated`, `ProductUpdated`, `ProductDeleted`, `PriceChanged`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/products/**`
- **Publishes (Kafka)**: `Product*`, `PriceChanged`
- **Consumed by**: `Search` (reindex), `Recommendation` (model updates), `Analytics/Reporting` (facts), `Cart` (price recalculation)
- **Resilience**: Versioned documents; eventual consistency to Search/Recommendations via events

```mermaid
sequenceDiagram
  autonumber
  participant PR as Product
  participant DP as Data Pipeline
  participant SR as Search
  participant RC as Recommendation
  PR->>DP: Publish ProductUpdated
  DP-->>SR: Trigger reindex
  DP-->>RC: Update features
```

#### Details
- **Responsibilities**: Authoritative product catalog (CRUD), pricing, categories, media metadata.
- **Primary endpoints**: `/api/products`, `/api/products/{id}`, `/api/products/{id}/exists`
- **Data model hints**: `Product{id, sku, title, price, currency, attributes{}, status}`
- **Events purpose**: Drive Search reindex, Recommendation features, Analytics facts.
- **Dependencies**: Consumed by `Cart` (price), `Order` (details), `Search` (index), `Recommendation`.
- **Auth/Roles**: Read public; writes require `ADMIN/MERCHANT`.
- **Resilience**: Versioned docs; outbox to Kafka; eventual consistency to indices.
- **Observability**: Metrics `products.updated.count`, `products.price.change.count`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/products/)

##### Example Kafka Payloads
```json
{
  "eventType": "ProductUpdated",
  "productId": "SKU-123",
  "title": "Wireless Mouse",
  "price": {"amount": 29.99, "currency": "USD"},
  "attributes": {"color": "black"},
  "timestamp": "2024-01-01T12:10:00Z",
  "metadata": {"source": "product-service", "version": 3}
}
```

---

### 📊 **Inventory Service** `Port: 8084`
**Purpose**: Real-time stock management and tracking

**Key Features**:
- 📈 Stock level monitoring
- ⚠️ Low stock alerts
- 📦 Batch inventory updates
- 🔄 Automatic reordering
- 📊 Inventory analytics

**Database**: PostgreSQL | **Events**: `InventoryUpdated`, `LowStockAlert`, `OutOfStock`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/inventory/**`
- **Consumed by/Calls**: Called by `Cart` for availability; called by `Order` to reserve/commit/release
- **Publishes (Kafka)**: `InventoryUpdated`, `LowStockAlert`
- **Resilience**: Reservation tokens; time‑boxed holds; idempotent commit/release

```mermaid
sequenceDiagram
  autonumber
  participant OS as Order
  participant IV as Inventory
  OS->>IV: POST /reserve (sku, qty)
  IV-->>OS: 201 Reserved(reservationId)
  OS->>IV: POST /commit {reservationId}
  IV-->>OS: 200 Committed
```

#### Details
- **Responsibilities**: Track stock by SKU; reservations, commits, releases; low-stock alarms.
- **Primary endpoints**: `/api/inventory/check`, `/api/inventory/reserve`, `/api/inventory/commit`, `/api/inventory/release`
- **Data model hints**: `Inventory{sku, available, reserved, safetyStock}`; `Reservation{id, sku, qty, ttl}`
- **Events purpose**: `InventoryUpdated` updates projections/analytics; `LowStockAlert` triggers reorders/alerts.
- **Dependencies**: Called by `Order` and `Cart`; consumes `order-confirmed/cancelled`.
- **Resilience**: Idempotent reservation tokens; TTL holds; commit/release guarantees.
- **Observability**: Metrics `inventory.reserve.latency`, `inventory.lowstock.count`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/inventory/)

##### Example Kafka Payloads
```json
{
  "eventType": "InventoryUpdated",
  "sku": "SKU-123",
  "available": 120,
  "reserved": 5,
  "lowStock": false,
  "timestamp": "2024-01-01T12:12:00Z",
  "metadata": {"source": "inventory-service", "version": 2}
}
```

---

### 🛒 **Cart Service** `Port: 8085`
**Purpose**: Shopping cart management with Redis caching

**Key Features**:
- 🛍️ Add/remove items from cart
- 💰 Real-time price calculations
- ⏰ Cart persistence and expiration
- 🔄 Cart synchronization across devices
- 💳 Checkout preparation

**Database**: Redis | **Events**: `ItemAddedToCart`, `ItemRemovedFromCart`, `CartAbandoned`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/cart/**`
- **Calls**: `Inventory` for stock check; enriches with `Product` price
- **Publishes (Kafka)**: `Cart*` for analytics and recommendations
- **Resilience**: Optimistic updates; TTL expiration; debounce stock checks

---
```mermaid
sequenceDiagram
  autonumber
  participant CT as Cart
  participant PR as Product
  participant IV as Inventory
  participant RC as Recommendation
  CT->>PR: GET product price/details
  CT->>IV: Check stock (sku, qty)
  alt In stock
    CT-->>RC: Publish ItemAddedToCart
  else Out of stock
    CT-->>CT: Reject add/update
  end
```

#### Details
- **Responsibilities**: Manage user carts; validate items; compute totals; prepare checkout.
- **Primary endpoints**: `/api/cart`, `/api/cart/items`, `/api/cart/clear`
- **Data model hints**: `Cart{userId, items[{sku, qty, unitPrice}], total}`
- **Events purpose**: `Cart*` events feed Analytics and Recommendation.
- **Dependencies**: Calls `Product` for price/details; `Inventory` for availability.
- **Resilience**: Redis-backed TTL; optimistic updates; debounce stock checks.
- **Observability**: Metrics `cart.item.added.count`, `cart.abandoned.count`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/cart/)

##### Example Kafka Payloads
```json
{
  "eventType": "ItemAddedToCart",
  "userId": "b1b2c3d4",
  "sku": "SKU-123",
  "qty": 1,
  "unitPrice": 29.99,
  "timestamp": "2024-01-01T12:05:00Z",
  "metadata": {"source": "cart-service", "version": 1}
}
```

---

### 📋 **Order Service** `Port: 8086`
**Purpose**: Complete order lifecycle management

**Key Features**:
- 📝 Order creation and validation
- 📊 Order status tracking
- 🔄 Order modification and cancellation
- 📧 Order notifications
- 📈 Order analytics

**Database**: PostgreSQL | **Events**: `OrderCreated`, `OrderUpdated`, `OrderCancelled`, `OrderCompleted`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/orders/**`
- **Calls**: `Payment` (authorize/capture/refund), `Inventory` (reserve/commit/release), `Delivery` (shipment)
- **Publishes (Kafka)**: `Order*` for `Notification`, `Analytics`, `Audit`
- **Resilience**: Saga orchestration; compensations on failure (release stock, refund)

---
<details>
  <summary>Order → Inventory → Payment → Delivery (Saga)</summary>

```mermaid
sequenceDiagram
  autonumber
  participant OR as Order
  participant IV as Inventory
  participant PM as Payment
  participant DV as Delivery
  OR->>IV: Reserve(items)
  IV-->>OR: reservationId
  OR->>PM: Authorize(amount)
  PM-->>OR: PaymentProcessed | PaymentFailed
  alt Success
    OR->>IV: Commit(reservationId)
    OR->>DV: Create shipment
    OR-->>OR: Publish order-confirmed
  else Failure
    OR->>IV: Release(reservationId)
    OR-->>OR: Publish order-cancelled
  end
```

</details>

---

### 💳 **Payment Service** `Port: 8087`
**Purpose**: Secure payment processing and transaction management

**Key Features**:
- 💰 Multi-gateway payment processing
- 🔒 PCI DSS compliant transactions
- 💸 Refund and chargeback handling
- 📊 Payment analytics
- 🔄 Recurring payment support

**Database**: PostgreSQL | **Integrations**: Stripe, PayPal, Square
**Events**: `PaymentProcessed`, `PaymentFailed`, `RefundIssued`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/payments/**`
- **Consumed by/Calls**: Called by `Order` for authorize/capture; emits events consumed by `Order` for state
- **Resilience**: Idempotency keys; provider retries; webhook reconciliation

<details>
  <summary>Payment Service – Authorize/Capture Sequence</summary>

```mermaid
sequenceDiagram
  autonumber
  participant OS as Order
  participant PS as Payment
  OS->>PS: POST /authorize {orderId, amount}
  PS-->>OS: PaymentProcessed | PaymentFailed
  OS->>PS: POST /capture {paymentId}
  PS-->>OS: PaymentProcessed
```

</details>

#### Details
- **Responsibilities**: Authorize/capture/refund payments; reconcile webhooks; publish payment status.
- **Primary endpoints**: `/api/payments` (authorize/capture/refund endpoints as per controller)
- **Data model hints**: `Payment{id, orderId, amount, currency, status, provider, txnId}`
- **Events purpose**: `PaymentProcessed/Failed/RefundIssued` synchronize `Order`, Analytics, Audit.
- **Dependencies**: Called by `Order`; integrates with Stripe/PayPal; emits to Kafka.
- **Security**: PCI-safe handling; secrets via `Config Server`.
- **Resilience**: Idempotency keys; webhook retries; provider failover.
- **Observability**: Metrics `payments.auth.success.rate`, `payments.latency.ms`, `refunds.count`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/payments/)

##### Example Kafka Payloads
```json
{
  "eventType": "PaymentProcessed",
  "paymentId": "PAY-7788",
  "orderId": "ORD-10001",
  "amount": {"amount": 59.98, "currency": "USD"},
  "provider": "stripe",
  "status": "CAPTURED",
  "timestamp": "2024-01-01T12:16:30Z",
  "metadata": {"source": "payment-service", "version": 2}
}
```

---

### 🚛 **Delivery Service** `Port: 8088`
**Purpose**: Shipment creation, courier integration, and delivery tracking

**Key Features**:
- 🚚 Shipment label generation and tracking links
- 🔄 Integration with carriers (UPS, FedEx, DHL)
- 📍 Real-time delivery status updates
- ♻️ Return and RMA handling
- 🧭 Address validation

**Database**: PostgreSQL | **Events**: `ShipmentCreated`, `ShipmentDispatched`, `DeliveryCompleted`, `ReturnInitiated`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/delivery/**`
- **Consumed by/Calls**: Triggered by `OrderCompleted`; calls external carriers; updates `Order`
- **Resilience**: Outbox for webhooks; retry with backoff; DLQ for failed updates

---
<details>
  <summary>Delivery – Shipment Flow and Notifications</summary>

```mermaid
sequenceDiagram
  autonumber
  participant OR as Order
  participant DV as Delivery
  participant NT as Notification
  OR-->>DV: order-confirmed
  DV->>DV: Create shipment (carrier API)
  DV-->>OR: ShipmentCreated/Dispatched
  DV-->>NT: Delivery updates (events)
```

</details>

#### Details
- **Responsibilities**: Create and manage shipments; integrate with carriers; update delivery status.
- **Primary endpoints**: `/api/delivery/shipments`, `/api/delivery/shipments/{id}`
- **Data model hints**: `Shipment{id, orderId, carrier, tracking, status}`
- **Events purpose**: `ShipmentCreated/Dispatched/DeliveryCompleted` inform `Order`, `Notification`, `Analytics`.
- **Dependencies**: Triggered by `order-confirmed`; calls carrier APIs; updates `Order`.
- **Resilience**: Outbox for webhooks to carriers; retry/backoff; DLQ for failed updates.
- **Observability**: Metrics `shipment.created.count`, `delivery.latency.ms`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/delivery/)

##### Example Kafka Payloads
```json
{
  "eventType": "ShipmentCreated",
  "shipmentId": "SHP-5678",
  "orderId": "ORD-10001",
  "carrier": "UPS",
  "tracking": "1Z999",
  "status": "CREATED",
  "timestamp": "2024-01-01T12:20:00Z",
  "metadata": {"source": "delivery-service", "version": 1}
}
```

---

### 📧 **Notification Service** `Port: 8089`
**Purpose**: Multi-channel messaging (email, SMS, push)

**Key Features**:
- ✉️ Transactional emails (order, payment, delivery)
- 📱 SMS OTP and alerts
- 🔔 Push notifications
- 📦 Templating with variables and localization
- 📈 Delivery analytics and retries

**Integrations**: SendGrid, Twilio | **Events**: Consumes events from all business services

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/notifications/**`
- **Consumes (Kafka)**: `Order*`, `Payment*`, `Delivery*`, `UserRegistered`
- **Resilience**: Exponential backoff; provider failover; DLQ and replays

```mermaid
flowchart LR
  EV[Domain Events] -->|Kafka| NS[Notification]
  NS --> Email
  NS --> SMS
  NS --> Push
```

#### Details
- **Responsibilities**: Send transactional and marketing messages via Email/SMS/Push.
- **Primary endpoints**: `/api/notifications/send`, `/api/notifications/templates`
- **Data model hints**: `Notification{id, channel, template, to, status, error}`
- **Events purpose**: Consumes domain events to trigger user-facing communications.
- **Dependencies**: Providers like SendGrid/Twilio; subscribes to `Order*`, `Payment*`, `Delivery*`, `UserRegistered`.
- **Resilience**: Exponential backoff, provider failover, DLQ and replay.
- **Observability**: Metrics `notifications.sent.count`, `notifications.bounce.count`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/notifications/)

##### Example Kafka Payloads
```json
{
  "eventType": "NotificationSent",
  "notificationId": "NT-2222",
  "channel": "EMAIL",
  "to": "alice@example.com",
  "template": "order-confirmed",
  "status": "SENT",
  "timestamp": "2024-01-01T12:22:00Z",
  "metadata": {"source": "notification-service", "version": 1}
}
```

---

### ⭐ **Review Service** `Port: 8090`
**Purpose**: Product reviews, ratings, and moderation

**Key Features**:
- ⭐ Star ratings and text reviews
- 🧑‍⚖️ Moderation queue and abuse reports
- 🏷️ Review tags and helpful votes
- 🔍 Filter/sort by rating, date, helpfulness
- 🔗 Links to products and users

**Database**: PostgreSQL | **Events**: `ReviewCreated`, `ReviewUpdated`, `ReviewFlagged`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/reviews/**`
- **Consumed by/Calls**: Enriches with `User` and `Product`; emits moderation events for `Notification`
- **Resilience**: Content moderation queue; anti‑abuse throttling

---
<details>
  <summary>Review Service – Interactions</summary>

```mermaid
flowchart LR
  RV[Review] -->|Create/Update| EV{Emit Events}
  EV --> NT[Notification]
  RV -->|Fetch| US[User]
  RV -->|Fetch| PR[Product]
```

</details>

#### Details
- **Responsibilities**: Manage product reviews/ratings and moderation.
- **Primary endpoints**: `/api/reviews`, `/api/reviews/{id}`, `/api/reviews/product/{productId}`
- **Data model hints**: `Review{id, productId, userId, rating, comment, status}`
- **Events purpose**: `ReviewCreated/Updated/Flagged` enable moderation and notifications.
- **Dependencies**: Reads `User` and `Product` for enrichment; notifies `Notification` on moderation.
- **Resilience**: Anti-abuse throttling; moderation queue.
- **Observability**: Metrics `reviews.created.count`, `reviews.avg.rating`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/reviews/)

##### Example Kafka Payloads
```json
{
  "eventType": "ReviewCreated",
  "reviewId": "REV-1234",
  "productId": "PROD-123",
  "userId": "b1b2c3d4",
  "rating": 5,
  "comment": "Great product!",
  "status": "PENDING",
  "timestamp": "2024-01-01T12:18:00Z",
  "metadata": {"source": "review-service", "version": 1}
}
```

---

### 🔍 **Search Service** `Port: 8091`
**Purpose**: Full-text and faceted product search

**Key Features**:
- 🔎 Full-text search with highlighting
- 🏷️ Facets: category, brand, price, rating
- ⚡ Suggest/Autocomplete
- 🔄 Reindex on product changes
- 📈 Search analytics

**Search**: Elasticsearch/OpenSearch | **Events**: Consumes `ProductCreated/Updated/Deleted`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/search/**`
- **Consumes (Kafka)**: `Product*` events to reindex
- **Resilience**: Bulk indexing with retries; circuit breaker on ES

---
<details>
  <summary>Search Service – Indexing Flow</summary>

```mermaid
flowchart LR
  PR[Product Events] -->|Kafka| SR[Search Indexer]
  SR --> ES[(Elasticsearch)]
  Client -->|/api/search| SR
```

</details>

#### Details
- **Responsibilities**: Full-text search and facets; maintain index from product events.
- **Primary endpoints**: `/api/search?q=...&filters=...`
- **Data model hints**: Indexed document `ProductIndex{id, title, tokens[], facets}`
- **Events purpose**: Consume `Product*` to update search index.
- **Dependencies**: Elasticsearch/OpenSearch; product events via Kafka.
- **Resilience**: Bulk indexing with retries; circuit breaker to ES.
- **Observability**: Metrics `search.query.latency`, `indexing.backlog.size`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/search/)

##### Example Kafka Payloads
```json
{
  "eventType": "SearchIndexUpdated",
  "productId": "SKU-123",
  "action": "UPSERT",
  "timestamp": "2024-01-01T12:25:00Z",
  "metadata": {"source": "search-service", "version": 1}
}
```

---

### 🎯 **Recommendation Service** `Port: 8092`
**Purpose**: Personalized recommendations and cross-sell/upsell

**Key Features**:
- 🧠 Collaborative filtering (views, carts, purchases)
- 🛒 "People also bought" and "Similar items"
- 🔄 Real-time updates from events
- 🧪 A/B testing for models
- 📊 CTR/conversion tracking

**Database**: Redis/PostgreSQL | **Events**: Consumes from `Order`, `Cart`, `Product`, `User`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/recommendations/**`
- **Consumes (Kafka)**: `Order*`, `Cart*`, `Product*`, `User*`
- **Resilience**: Model versioning; fallback to popular items

---
<details>
  <summary>Recommendation Service – Event Flow</summary>

```mermaid
flowchart LR
  EV[Orders/Cart/Product/User Events] --> RC[Recommendation]
  RC --> FEED[Personalized Feed]
  Client -->|/api/recommendations| RC
```

</details>

#### Details
- **Responsibilities**: Generate personalized recommendations.
- **Primary endpoints**: `/api/recommendations/{userId}`
- **Data model hints**: `Recommendation{userId, items[{productId, score}]}`
- **Events purpose**: Consume behavior signals from `Order/Cart/Product/User`.
- **Dependencies**: Redis/Postgres for models and features; product catalog for lookups.
- **Resilience**: Model version fallbacks; cache warmers.
- **Observability**: Metrics `recommendations.served.count`, `ctr.rate`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/recommendations/)

##### Example Kafka Payloads
```json
{
  "eventType": "RecommendationServed",
  "userId": "b1b2c3d4",
  "items": [{"productId": "SKU-123", "score": 0.91}],
  "timestamp": "2024-01-01T12:26:00Z",
  "metadata": {"source": "recommendation-service", "version": 1}
}
```

---

### 📊 **Analytics Service** `Port: 8093`
**Purpose**: Real-time business metrics and dashboards

**Key Features**:
- 📈 Sales, revenue, and funnel metrics
- 👥 Cohort and retention analysis
- 🧭 Customer journeys
- 🗂️ Aggregations by product/category/region
- 🧵 Stream processing via Kafka Streams

**Warehouse**: PostgreSQL/Parquet | **Events**: Consumes all business events via `Data Pipeline`

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/analytics/**`
- **Consumes**: Streams from `Data Pipeline`; exposes aggregates to `Reporting`
- **Resilience**: Exactly‑once semantics with Kafka Streams; replayable sinks

---
<details>
  <summary>Analytics – Data Pipeline to Reporting</summary>

```mermaid
flowchart LR
  DP[Data Pipeline Streams] --> AN[Analytics Aggregator]
  AN --> RP[Reporting]
  Client -->|/api/analytics| AN
```

</details>

#### Details
- **Responsibilities**: Compute and expose business metrics and aggregates.
- **Primary endpoints**: `/api/analytics/metrics`, `/api/analytics/dashboards/{id}`
- **Data model hints**: `Metric{name, value, dims{}, ts}`
- **Events purpose**: Consume streams from `Data Pipeline` for near-real-time insights.
- **Dependencies**: Kafka Streams topologies; sinks to Reporting.
- **Resilience**: Exactly-once semantics; replayable sinks.
- **Observability**: Metrics `analytics.pipeline.lag.ms`, `analytics.query.latency`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/analytics/)

##### Example Kafka Payloads
```json
{
  "eventType": "MetricComputed",
  "name": "daily_sales_usd",
  "value": 12345.67,
  "dimensions": {"region": "NA"},
  "timestamp": "2024-01-01T12:30:00Z",
  "metadata": {"source": "analytics-service", "version": 1}
}
```

---

### 🚪 **API Gateway** `Port: 8080`
**Purpose**: Single entry point, routing, and cross-cutting concerns

**Key Features**:
- 🔀 Request routing and path rewriting
- 🔒 Authentication and rate limiting
- 🧾 Centralized logging and tracing
- 🧰 Canary and blue/green support
- 🧪 Fault injection for testing

**Tech**: Spring Cloud Gateway | **Depends on**: `Service Registry`, `Auth`

**Interactions**:
- **Ingress**: All client traffic
- **Egress**: Routes to services by path predicate `/api/<service>/**`
- **Resilience**: Rate limiting, retries, circuit breakers, tracing

---
<details>
  <summary>API Gateway – AuthZ and Proxy Flow</summary>

```mermaid
sequenceDiagram
  autonumber
  participant CL as Client
  participant GW as API Gateway
  participant AU as Auth
  participant SVC as Target Service
  CL->>GW: /api/<service>/...
  GW->>AU: Validate JWT (filter)
  AU-->>GW: OK/Forbidden
  alt Authorized
    GW->>SVC: Proxy request
    SVC-->>GW: Response
    GW-->>CL: Response
  else Rejected
    GW-->>CL: 401/403
  end
```

</details>

#### Details
- **Responsibilities**: Route, authenticate, and enforce cross-cutting concerns for all APIs.
- **Primary endpoints**: `/api/<service>/**`
- **Policies**: Rate limiting, authN/Z via filters, path rewriting, retries, circuit breakers.
- **Dependencies**: `Service Registry` (discovery), `Auth` (JWT), `Config Server` (policies).
- **Resilience**: Backpressure, timeouts, hedging (optional), fault injection for chaos.
- **Observability**: Access logs, traces, and request metrics.

---

### 🌐 **Service Registry** `Port: 8761`
**Purpose**: Service discovery

**Key Features**:
- 🧭 Eureka registry and dashboard
- 🔄 Heartbeat and instance health
- ♻️ Self-healing via re-registration

**Interactions**:
- **Ingress**: Service registrations
- **Egress**: Service lookup by clients/Gateway

---
<details>
  <summary>Service Registry – Discovery</summary>

```mermaid
flowchart LR
  SVC1[Service A] --> RG[Eureka]
  SVC2[Service B] --> RG
  GW[Gateway] -->|Discover| RG
  SVC1 & SVC2 -->|Discover peers| RG
```

</details>

#### Details
- **Responsibilities**: Maintain service instance registry and enable discovery.
- **Endpoints**: Eureka dashboard; client registration/heartbeat.
- **Dependencies**: Used by `Gateway` and services to find peers.
- **Resilience**: Self-healing via heartbeat; TTL-based evictions.
- **Observability**: Metrics `eureka.registry.size`, `instance.renewals`.

---

### ⚙️ **Config Server** `Port: 8888`
**Purpose**: Centralized configuration management

**Key Features**:
- 🗂️ Profiles per environment
- 🔐 Encrypted secrets (Vault-ready)
- 🔄 Dynamic refresh via Spring Cloud Bus

**Interactions**:
- **Ingress**: `/actuator/refresh` via Bus; clients bootstrap from Config
- **Resilience**: Cached config; fallback defaults

---
<details>
  <summary>Config Server – Bootstrap and Refresh</summary>

```mermaid
sequenceDiagram
  autonumber
  participant CF as Config Server
  participant SV as Service
  SV->>CF: GET config on startup
  CF-->>SV: application.yml (profile)
  SV-->>CF: /actuator/refresh (via Bus)
```

</details>

#### Details
- **Responsibilities**: Serve centralized config to services across environments.
- **Endpoints**: `/actuator/refresh` via Bus; config retrieval on bootstrap.
- **Dependencies**: Backed by Git or filesystem; integrated with Spring Cloud.
- **Resilience**: Cached configs; fallback defaults; secret encryption.
- **Observability**: Metrics `config.requests.count`, `refresh.events.count`.

---

### 📡 **Monitoring Service** `Port: 8094`
**Purpose**: System-wide health and SLO tracking

**Key Features**:
- 📈 Prometheus metrics collection
- 📊 Grafana dashboards
- 🧵 Distributed tracing (Zipkin/OTel)

**Interactions**:
- **Ingress**: Scrapes `/actuator/prometheus`, receives traces/logs
- **Resilience**: Backpressure; sampling

---
<details>
  <summary>Monitoring – Metrics, Dashboards, Alerts</summary>

```mermaid
flowchart LR
  SVC[Services] --> MN[Monitoring]
  MN --> Dash[Dashboards]
  MN --> Alerts[Alerts]
```

</details>

#### Details
- **Responsibilities**: Collect metrics/traces/logs and expose dashboards/alerts.
- **Endpoints**: Prometheus scrape `/actuator/prometheus`, Grafana dashboards.
- **Dependencies**: OTel/Zipkin instrumentation; Alertmanager.
- **Resilience**: Sampling and retention policies.
- **Observability**: SLOs, alert rules, and service-level metrics.

---

### 📋 **Logging Service** `Port: 8095`
**Purpose**: Centralized log aggregation

**Key Features**:
- 📦 Log shipping (Filebeat/Fluentd)
- 🔎 Elasticsearch indexing
- 📄 Kibana dashboards

**Interactions**:
- **Ingress**: Log shippers
- **Egress**: ES/Kibana

---
<details>
  <summary>Logging – Shipping and Visualization</summary>

```mermaid
flowchart LR
  SVC[Services] -->|Filebeat/Fluentd| LG[Logging]
  LG --> ES[(Elasticsearch)]
  ES --> KB[Kibana]
```

</details>

#### Details
- **Responsibilities**: Centralize logs and provide query/visualization.
- **Components**: Shippers (Filebeat/Fluentd) → Elasticsearch → Kibana.
- **Resilience**: Backpressure and buffering; index lifecycle policies.
- **Observability**: Log ingestion rates, error rates, index health.

---

### 📈 **Reporting Service** `Port: 8096`
**Purpose**: Operational and business reports

**Key Features**:
- 🗓️ Scheduled PDF/Excel reports
- 🔍 Ad-hoc query endpoints
- 🔐 Row-level security for reports

**Interactions**:
- **REST (via Gateway)**: `http://localhost:8080/api/reports/**`
- **Consumes**: Aggregates from `Analytics`

---
<details>
  <summary>Reporting – From Analytics to Reports</summary>

```mermaid
flowchart LR
  AN[Analytics] --> RP[Reporting]
  Client -->|/api/reports| RP
```

</details>

#### Details
- **Responsibilities**: Deliver scheduled and ad-hoc business reports.
- **Primary endpoints**: `/api/reports`, `/api/reports/{id}`, `/api/reports/run`
- **Data model hints**: `Report{id, name, params{}, format, status}`
- **Dependencies**: Reads aggregates from `Analytics`.
- **Resilience**: Scheduled retries; caching of report artifacts.
- **Observability**: Metrics `reports.generated.count`, `report.latency.ms`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/reports/)

##### Example Kafka Payloads
```json
{
  "eventType": "ReportGenerated",
  "reportId": "REP-9090",
  "name": "weekly_sales",
  "format": "PDF",
  "status": "COMPLETED",
  "timestamp": "2024-01-01T12:35:00Z",
  "metadata": {"source": "reporting-service", "version": 1}
}
```

---

### 📝 **Audit Service** `Port: 8097`
**Purpose**: Compliance-grade audit trails

**Key Features**:
- 🧾 Immutable audit logs
- 🕒 Event timelines per entity
- 🔎 Tamper detection and retention policies

**Database**: PostgreSQL | **Events**: Consumes all significant domain events

**Interactions**:
- **Consumes (Kafka)**: All domain topics (immutable store)
- **REST (via Gateway)**: Browse/search audit trails

---
<details>
  <summary>Audit – Event Ingestion and Browsing</summary>

```mermaid
flowchart LR
  EV[Domain Events] --> AU[Audit Store]
  Client -->|Browse/Search| AU
```

</details>

#### Details
- **Responsibilities**: Immutable audit trail for compliance and forensics.
- **Data model hints**: `AuditEvent{id, type, entityType, entityId, userId, ts, payload}`
- **Dependencies**: Consumes all significant domain events.
- **Observability**: Audit completeness and retention metrics.

---

### 💾 **Backup Service** `Port: 8098`
**Purpose**: Automated backups and restores

**Key Features**:
- ☁️ Offsite backups to S3-compatible storage
- ♻️ Retention policies and lifecycle rules
- 🧪 Restore validation

**Interactions**:
- **Ingress**: Schedulers trigger backups
- **Egress**: Object storage

---
<details>
  <summary>Backup – To Object Storage</summary>

```mermaid
flowchart LR
  SC[Scheduler] --> BK[Backup]
  BK --> S3[(Object Storage)]
```

</details>

#### Details
- **Responsibilities**: Automated backups and restores to object storage.
- **Endpoints**: Managed internally; triggered by Scheduler.
- **Resilience**: Verification and retention policies.
- **Observability**: Metrics `backup.success.count`, `backup.duration.ms`.

---

### ⏰ **Scheduler Service** `Port: 8099`
**Purpose**: Coordinated cron and distributed locks

**Key Features**:
- 🕰️ Cron jobs with ShedLock
- 🔁 Retry and backoff policies
- 📢 Publishes maintenance events

**Interactions**:
- **Publishes (Kafka)**: Maintenance/cleanup events
- **Triggers**: Backups, report generation, reindexing

---
<details>
  <summary>Scheduler – Cron Triggers</summary>

```mermaid
flowchart LR
  SC[Scheduler] -->|Cron| BK[Backup]
  SC --> RP[Reporting]
  SC --> SR[Search Reindex]
```

</details>

#### Details
- **Responsibilities**: Run scheduled jobs with distributed locks.
- **Endpoints**: Cron job definitions; triggers for backups/reports/reindexing.
- **Resilience**: ShedLock for single-run guarantees.
- **Observability**: Metrics `jobs.executed.count`, `job.failure.count`.

---

### 🌊 **Data Pipeline Service** `Port: 8100`
**Purpose**: Stream ingestion, transformation, and routing

**Key Features**:
- 🧵 Kafka Streams topologies
- 🔄 ETL to analytics/reporting sinks
- 🧹 PII scrubbing and schema validation

**Interactions**:
- **Consumes**: All business topics; transforms and sinks to `Analytics/Reporting`
- **Resilience**: Exactly‑once processing; schema registry validation

---
```mermaid
flowchart LR
  EV[Business Topics] --> DP[Data Pipeline]
  DP --> AN[Analytics]
  DP --> RP[Reporting]
```

#### Details
- **Responsibilities**: Ingest/transform/route domain streams to sinks.
- **Endpoints**: `/api/pipeline/**` for management/health.
- **Resilience**: Exactly-once processing; schema validation via registry.
- **Observability**: Metrics `pipeline.lag.ms`, `pipeline.error.rate`.

##### Quick Links
- [API via Gateway](http://localhost:8080/api/pipeline/)

##### Example Kafka Payloads
```json
{
  "eventType": "PipelineBatchProcessed",
  "batchId": "BATCH-20240101-01",
  "records": 50000,
  "lagMs": 1200,
  "timestamp": "2024-01-01T12:40:00Z",
  "metadata": {"source": "data-pipeline-service", "version": 1}
}
```

---

### 🔧 **Common Library**
**Purpose**: Shared DTOs, events, utilities, and security

**Key Features**:
- 🧱 Event schemas and headers
- 🛡️ Security filters and JWT utilities
- 🧰 Error handling and tracing helpers

**Interactions**:
- **Used by**: All services; guarantees consistent contracts

---
<details>
  <summary>Common Library – Usage Across Services</summary>

```mermaid
flowchart LR
  CL[Common Library] --> US[User]
  CL --> PR[Product]
  CL --> OR[Order]
  CL --> PM[Payment]
  CL --> DV[Delivery]
  CL --> Others[All Services]
```

</details>

#### Details
- **Responsibilities**: Provide shared DTOs, event schemas, security, and error handling.
- **Contracts**: Event headers, envelope schemas, error formats.
- **Observability**: Versioned artifacts; changelog for breaking changes.

---

## 🔄 Service Interactions

## 📘 Swagger UI Links
Conventional Springdoc paths (if enabled per service):

- User Service: http://localhost:8081/swagger-ui/index.html
- Auth Service: http://localhost:8082/swagger-ui/index.html
- Product Service: http://localhost:8083/swagger-ui/index.html
- Inventory Service: http://localhost:8084/swagger-ui/index.html
- Cart Service: http://localhost:8085/swagger-ui/index.html
- Order Service: http://localhost:8086/swagger-ui/index.html
- Payment Service: http://localhost:8087/swagger-ui/index.html
- Delivery Service: http://localhost:8088/swagger-ui/index.html
- Notification Service: http://localhost:8089/swagger-ui/index.html
- Review Service: http://localhost:8090/swagger-ui/index.html
- Search Service: http://localhost:8091/swagger-ui/index.html
- Recommendation Service: http://localhost:8092/swagger-ui/index.html
- Analytics Service: http://localhost:8093/swagger-ui/index.html
- Monitoring Service: http://localhost:8094/swagger-ui/index.html
- Logging Service: http://localhost:8095/swagger-ui/index.html
- Reporting Service: http://localhost:8096/swagger-ui/index.html
- Audit Service: http://localhost:8097/swagger-ui/index.html
- Backup Service: http://localhost:8098/swagger-ui/index.html
- Scheduler Service: http://localhost:8099/swagger-ui/index.html
- Data Pipeline Service: http://localhost:8100/swagger-ui/index.html

Note: some platform services may not expose Swagger UIs or keep them disabled in production profiles.

## 🧾 Event Schema Index
Reference of common event payloads from `common/` used across services. Base envelope fields come from `BaseEvent`.

- **BaseEvent** (`common/src/main/java/com/ironsoftware/common/events/BaseEvent.java`)
  - `eventId: string`, `eventType: string`, `timestamp: Instant`, `source: string`, `version: string`

- **UserRegisteredEvent** (`.../events/user/UserRegisteredEvent.java`)
  - Extends `BaseEvent`
  - `userId: string`, `email: string`, `username: string`, `emailVerified: boolean`

- **OrderCreatedEvent** (`.../events/OrderCreatedEvent.java`)
  - `orderId: string`, `userId: string`, `productId: string`, `quantity: int`

- **PaymentProcessedEvent** (`.../events/PaymentProcessedEvent.java`)
  - `orderId: string`, `userId: string`, `amount: BigDecimal`, `transactionId: string`, `status: string`, `processedAt: LocalDateTime`

- **InventoryUpdatedEvent** (`.../events/InventoryUpdatedEvent.java`)
  - `productId: string`, `quantity: int`, `availableStock: int`, `status: string`, `orderId: string`

- **DeliveryCreatedEvent** (`.../events/delivery/DeliveryCreatedEvent.java`)
  - Extends `BaseEvent`
  - `deliveryId, trackingNumber, orderId, customerId, recipientName, street, city, state, country, zipCode, phoneNumber, pickupLongitude, pickupLatitude, deliveryLongitude, deliveryLatitude`

<sub>Additions like `DeliveryStatusUpdatedEvent` and `UserActivityEvent` follow similar patterns.</sub>

## 📄 OpenAPI JSON via Gateway
Direct links to OpenAPI specs through the API Gateway:

- User: http://localhost:8080/api/users/v3/api-docs
- Auth: http://localhost:8080/api/auth/v3/api-docs
- Product: http://localhost:8080/api/products/v3/api-docs
- Inventory: http://localhost:8080/api/inventory/v3/api-docs
- Cart: http://localhost:8080/api/cart/v3/api-docs
- Order: http://localhost:8080/api/orders/v3/api-docs
- Payment: http://localhost:8080/api/payments/v3/api-docs
- Delivery: http://localhost:8080/api/delivery/v3/api-docs
- Notification: http://localhost:8080/api/notifications/v3/api-docs
- Review: http://localhost:8080/api/reviews/v3/api-docs
- Search: http://localhost:8080/api/search/v3/api-docs
- Recommendation: http://localhost:8080/api/recommendations/v3/api-docs
- Analytics: http://localhost:8080/api/analytics/v3/api-docs
- Reporting: http://localhost:8080/api/reports/v3/api-docs
- Data Pipeline: http://localhost:8080/api/pipeline/v3/api-docs

### 📋 Quick Interaction Matrix

| Service | REST via Gateway | Publishes (Kafka) | Consumes (Kafka) |
|---|---|---|---|
| User | /api/users/** | UserRegistered, UserProfileUpdated, UserDeleted | — |
| Auth | /api/auth/** | UserAuthenticated, LoginFailed | — |
| Product | /api/products/** | ProductCreated/Updated/Deleted, PriceChanged | — |
| Inventory | /api/inventory/** | InventoryUpdated, LowStockAlert | order-confirmed, order-cancelled |
| Cart | /api/cart/** | Cart* | — |
| Order | /api/orders/** | order-created, order-confirmed, order-cancelled | payment-events |
| Payment | /api/payments/** | payment-events (processed/failed/refund) | — |
| Delivery | /api/delivery/** | ShipmentCreated/Dispatched/Completed, ReturnInitiated | order-confirmed |
| Notification | /api/notifications/** | — | Order*, Payment*, Delivery*, UserRegistered |
| Review | /api/reviews/** | ReviewCreated/Updated/Flagged | — |
| Search | /api/search/** | — | Product* |
| Recommendation | /api/recommendations/** | — | Order*, Cart*, Product*, User* |
| Analytics | /api/analytics/** | — | All via Data Pipeline |
| Reporting | /api/reports/** | — | Aggregates from Analytics |
| API Gateway | /api/<service>/** | — | — |
| Service Registry | n/a | — | — |
| Config Server | n/a | — | — |
| Monitoring | n/a | — | Metrics/Traces/Logs |
| Logging | n/a | — | Logs from services |
| Audit | n/a | — | All significant domain events |
| Backup | n/a | — | Scheduler triggers |
| Scheduler | n/a | Maintenance events | — |
| Data Pipeline | /api/pipeline/** | Transformed streams | All business topics |
| Common Library | n/a | — | — |

### 🌐 End-to-End All-Services Sequence (Full Picture)

<details>
  <summary>All Services – End-to-End Sequence</summary>

```mermaid
sequenceDiagram
  autonumber
  participant CL as Client
  participant GW as API Gateway
  participant RG as Service Registry
  participant CF as Config Server
  participant LG as Logging
  participant MN as Monitoring
  participant US as User
  participant AS as Auth
  participant PR as Product
  participant CT as Cart
  participant IV as Inventory
  participant OR as Order
  participant PM as Payment
  participant DV as Delivery
  participant NT as Notification
  participant RV as Review
  participant SR as Search
  participant RC as Recommendation
  participant AN as Analytics
  participant RP as Reporting
  participant AD as Audit
  participant BK as Backup
  participant SC as Scheduler
  participant DP as Data Pipeline

  note over GW,CF: Platform bootstraps (CF config, RG discovery, GW routes). LG/MN collect logs/metrics throughout.

  CL->>GW: 1) POST /api/users/register
  GW->>US: Create user
  US-->>AD: Event UserRegistered
  US-->>NT: Event UserRegistered (welcome email)
  US-->>AN: Event UserRegistered (cohort)

  CL->>GW: 2) POST /api/auth/login
  GW->>AS: Authenticate, issue JWT
  AS-->>AD: Event UserAuthenticated

  CL->>GW: 3) GET /api/products?search=shoes
  GW->>PR: Query products
  PR-->>DP: Event ProductQueried (optional)
  PR-->>SR: Event ProductUpdated (index maintenance, if any)
  SR-->>PR: Search results (if delegated)
  PR-->>RC: Event ProductSignals (model features)

  CL->>GW: 4) POST /api/cart/items
  GW->>CT: Add to cart
  CT->>PR: Get product details/price
  CT->>IV: Check availability
  CT-->>DP: Event CartUpdated
  CT-->>RC: Event CartSignals

  CL->>GW: 5) POST /api/orders
  GW->>OR: Create order (PENDING)
  OR->>US: Validate user
  OR->>PR: Fetch product details
  OR->>IV: Reserve stock
  IV-->>OR: reservationId
  OR-->>AD: Event order-created
  OR-->>DP: Event order-created
  OR-->>NT: Event order-created

  CL->>GW: 6) PUT /api/orders/{id}/confirm?paymentMethodId=pm_abc
  GW->>OR: Confirm order
  OR->>PM: Authorize/Capture payment
  PM-->>OR: PaymentProcessed | PaymentFailed
  PM-->>DP: payment-events
  PM-->>AD: payment-events
  OR-->>IV: Commit or Release reservation
  OR-->>AD: order-confirmed | order-cancelled
  OR-->>DP: order-confirmed | order-cancelled
  OR-->>NT: order-confirmed | order-cancelled

  alt On confirmation
    OR->>DV: Create shipment
    DV-->>OR: ShipmentCreated/Dispatched
    DV-->>NT: Delivery updates events
    DV-->>DP: Delivery events
  else On cancellation
    OR->>PM: Refund (if needed)
    PM-->>OR: RefundIssued
    OR->>IV: Release reservation
  end

  %% Post-transaction analytics/reporting
  DP-->>AN: Stream all domain events
  AN-->>RP: Aggregated cubes
  RP->>GW: Expose reports via /api/reports

  %% Cross-cutting
  par Observability
    GW-->>LG: Access logs
    GW-->>MN: Request metrics/traces
    OR-->>LG: Service logs
    OR-->>MN: Business metrics/traces
    PM-->>LG: Logs; PM-->>MN: Metrics
  and Config/Discovery
    US->>CF: Fetch config on startup
    US->>RG: Register instance
    OR->>CF: Fetch config on startup
    OR->>RG: Register instance
    PM->>CF: Fetch config; PM->>RG: Register
  end

  %% Periodic operations
  SC-->>BK: Trigger scheduled backups
  BK-->>LG: Backup logs
  BK-->>MN: Backup metrics
```

</details>

### 🛒 **Complete E-Commerce Workflow**

```
1. 👤 Customer Registration
   User Service → Auth Service → Audit Service
   
2. 📦 Product Browse & Search
   Product Service → Search Service → Recommendation Service
   
3. 🛒 Shopping Cart
   Cart Service ↔ Inventory Service → Analytics Service
   
4. 📋 Order Processing
   Order Service → Payment Service → Inventory Service → Delivery Service
   
5. 📧 Notifications
   Notification Service ← Kafka Events ← All Services
   
6. 📊 Analytics & Reporting
   Analytics Service ← Data Pipeline Service ← Kafka Events
```

### 🔄 **Event-Driven Communication**

**Event Publishers**: User, Product, Order, Payment, Inventory, Delivery Services
**Message Broker**: Apache Kafka
**Event Consumers**: Notification, Analytics, Reporting, Audit, Recommendation, Search Services

---

### 📜 Sequence Diagram (Checkout Flow)

<details>
  <summary>Checkout Flow – Happy Path</summary>

```mermaid
sequenceDiagram
  autonumber
  actor U as User
  participant GW as API Gateway
  participant US as User Service
  participant CS as Cart Service
  participant IS as Inventory Service
  participant OS as Order Service
  participant PS as Payment Service
  participant DS as Delivery Service
  participant NS as Notification Service
  U->>GW: Add item to cart
  GW->>CS: POST /cart/items
  CS-->>IS: Check stock
  IS-->>CS: OK (reserved)
  U->>GW: Checkout
  GW->>OS: POST /orders
  OS->>PS: Initiate payment
  PS-->>OS: PaymentProcessed
  OS-->>DS: Create shipment
  DS-->>OS: ShipmentCreated
  OS-->>NS: Emit OrderCompleted event
  NS-->>U: Send confirmation
```

</details>

### 📣 Kafka Topics

- `user.events`: `UserRegistered`, `UserProfileUpdated`
- `product.events`: `ProductCreated`, `ProductUpdated`, `PriceChanged`
- `inventory.events`: `InventoryUpdated`, `LowStockAlert`
- `cart.events`: `ItemAddedToCart`, `ItemRemovedFromCart`, `CartAbandoned`
- `order.events`: `OrderCreated`, `OrderCompleted`, `OrderCancelled`
- `payment.events`: `PaymentProcessed`, `PaymentFailed`
- `delivery.events`: `ShipmentCreated`, `DeliveryCompleted`
- `notification.events`: Delivery receipts and bounces
- `audit.events`: Aggregated audit trail stream

---

## 🗺️ Visualizations

### 📚 Per‑Service Interaction Details

The following summarizes how each service communicates with others. Sync traffic is via the API Gateway (`/api/<service>/…`). Async communication uses Kafka topics listed above.

#### 👤 User Service
- **Ingress (REST)**: `POST /api/users/register`, `GET /api/users/{id}`
- **Egress (REST)**: Calls Auth for token validation when needed
- **Async (publish)**: `UserRegistered`, `UserProfileUpdated`
- **Async (consume)**: None
- **Used by**: Order, Notification, Analytics, Audit

#### 🔐 Auth Service
- **Ingress (REST)**: `POST /api/auth/login`, `POST /api/auth/refresh`
- **Egress (REST)**: N/A (stateless JWT)
- **Async**: Emits auth audit logs via `Audit` where applicable
- **Used by**: API Gateway (filters), all services (JWT validation)

#### 📦 Product Service
- **Ingress (REST)**: `GET /api/products`, `POST /api/products`
- **Async (publish)**: `ProductCreated/Updated/Deleted`, `PriceChanged`
- **Consumed by**: Search (reindex), Recommendation (model updates), Analytics/Reporting (facts), Inventory (stock association)

#### 📊 Inventory Service
- **Ingress (REST)**: `GET /api/inventory/{sku}`, `POST /api/inventory/reserve`
- **Async (publish)**: `InventoryUpdated`, `LowStockAlert`
- **Consumes**: `OrderCreated` (reserve), `OrderCancelled` (release)

#### 🛒 Cart Service
- **Ingress (REST)**: `POST /api/cart/items`, `DELETE /api/cart/items/{sku}`
- **Egress (REST)**: Inventory stock check during add/update
- **Async (publish)**: `ItemAddedToCart`, `ItemRemovedFromCart`, `CartAbandoned`
- **Consumes**: Price updates from Product for recalculation

#### 📋 Order Service
- **Ingress (REST)**: `POST /api/orders`, `PUT /api/orders/{id}/cancel`
- **Egress (REST)**: Payment (authorize/capture), Inventory (reserve/commit), Delivery (create shipment)
- **Async (publish)**: `OrderCreated`, `OrderCompleted`, `OrderCancelled`
- **Consumes**: `PaymentProcessed/Failed` to decide commit/rollback

#### 💳 Payment Service
- **Ingress (REST)**: `POST /api/payments/authorize`, `POST /api/payments/capture`
- **Async (publish)**: `PaymentProcessed`, `PaymentFailed`, `RefundIssued`
- **Consumes**: `OrderCreated` (optional: pre‑auth), `OrderCancelled` (refund)

#### 🚛 Delivery Service
- **Ingress (REST)**: `POST /api/delivery/shipments`
- **Async (publish)**: `ShipmentCreated`, `DeliveryCompleted`
- **Consumes**: `OrderCompleted` (ship), `OrderCancelled` (stop)

#### 📧 Notification Service
- **Ingress (REST)**: `POST /api/notifications/send` (admin/ops)
- **Async (consume)**: Listens to `Order*`, `Payment*`, `Delivery*`, `UserRegistered`
- **Channels**: Email/SMS/Push via providers; retries and DLQ

#### 🔍 Search Service
- **Async (consume)**: `Product*` events for indexing
- **Ingress (REST)**: `GET /api/search?q=...`

#### 🎯 Recommendation Service
- **Async (consume)**: `Order*`, `Cart*`, `Product*`, `User*`
- **Ingress (REST)**: `GET /api/recommendations/{userId}`

#### 📊 Analytics Service
- **Async (consume)**: Aggregates from Data Pipeline (`product`, `order`, `payment`, etc.)
- **Ingress (REST)**: `GET /api/analytics/...`

#### 📈 Reporting Service
- **Async (consume)**: Facts from Analytics or Pipeline sinks
- **Ingress (REST)**: `GET /api/reports/...`

#### 📝 Audit Service
- **Async (consume)**: All important domain events
- **Ingress (REST)**: `GET /api/audit/...` for browsing (optional)

#### 🌊 Data Pipeline Service
- **Async**: Stream processing/topologies. Sources from business topics → sinks to Analytics/Reporting stores.

#### ☸️ Platform (Gateway, Registry, Config)
- **Gateway**: Routes all REST; applies auth/rate‑limit/tracing
- **Registry**: Service discovery (Eureka)
- **Config**: Centralized config, dynamic refresh

---

### 🧭 Sequence: Payment Failure Rollback

<details>
  <summary>Order Flow – Payment Failure and Rollback</summary>

```mermaid
sequenceDiagram
  autonumber
  actor U as User
  participant GW as API Gateway
  participant OS as Order Service
  participant PS as Payment Service
  participant IS as Inventory Service
  participant NS as Notification
  U->>GW: Checkout
  GW->>OS: POST /orders
  OS->>IS: Reserve stock
  IS-->>OS: Reserved
  OS->>PS: Authorize payment
  PS-->>OS: PaymentFailed
  OS->>IS: Release stock
  OS-->>NS: Emit OrderCancelled
  NS-->>U: Payment failed notification
```

</details>

### ↩️ Sequence: Order Cancellation by User

<details>
  <summary>Order Cancellation – User Initiated</summary>

```mermaid
sequenceDiagram
  autonumber
  actor U as User
  participant GW as API Gateway
  participant OS as Order Service
  participant PS as Payment Service
  participant IS as Inventory Service
  participant DS as Delivery Service
  U->>GW: Cancel order
  GW->>OS: PUT /orders/{id}/cancel
  OS->>PS: Refund (if captured)
  PS-->>OS: RefundIssued
  OS->>IS: Release reserved stock
  OS->>DS: Halt shipment (if any)
  OS-->>U: Cancellation confirmed
```

</details>

### 🗂️ Event Flow Overview

<details>
  <summary>Event Flow – Producers, Topics, Consumers</summary>

```mermaid
graph LR
  subgraph Producers
    US[User]
    PR[Product]
    OR[Order]
    PM[Payment]
    IV[Inventory]
    DV[Delivery]
  end
  subgraph Topics
    UT((user.events))
    PT((product.events))
    OT((order.events))
    PMT((payment.events))
    IT((inventory.events))
    DT((delivery.events))
  end
  subgraph Consumers
    NS[Notification]
    AN[Analytics]
    RP[Reporting]
    AU[Audit]
    RC[Recommendation]
    SR[Search]
  end
  US-->UT
  PR-->PT
  OR-->OT
  PM-->PMT
  IV-->IT
  DV-->DT
  UT-->NS & AN & AU & RC
  PT-->SR & AN & AU & RC
  OT-->NS & AN & AU & RC
  PMT-->NS & AN & AU
  IT-->AN & AU
  DT-->NS & AN & AU
```

</details>

> Note: GitHub sanitizes inline SVG. If you see raw `<style>`/`<title>` text, use the static image below or open the repo in an IDE/local preview.

![Service Map (static)](docs/images/service-map-root.svg)

---
<p align="center">
  <a href="docs/interactive-service-map.html"><strong>Open Interactive Service Map (HTML)</strong></a>
  <br/>
  <sub>Full interactivity with tooltips and links; works on GitHub and locally.</sub>
</p>

#### How to use the interactive diagrams

- **Click any service node** to open its live API via the Gateway (e.g., `http://localhost:8080/api/<service>/`).
- **Tooltips** show the service name and route; **hover states** underline labels.
- **Dark mode** is supported automatically via `prefers-color-scheme`.
- **Infra nodes** (gateway, registry, config, logging, monitoring, etc.) link to their local READMEs.
- To make links work locally, start the stack (e.g., `docker-compose up` or project scripts) so the Gateway is at `http://localhost:8080`.
- If your base URL differs (Kubernetes/Ingress), update the hrefs or set up port-forwarding to match `8080`.

#### Quick links (Swagger & OpenAPI)

- User: [Swagger UI](http://localhost:8080/api/users/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/users/v3/api-docs)
- Product: [Swagger UI](http://localhost:8080/api/products/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/products/v3/api-docs)
- Cart: [Swagger UI](http://localhost:8080/api/cart/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/cart/v3/api-docs)
- Order: [Swagger UI](http://localhost:8080/api/orders/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/orders/v3/api-docs)
- Payment: [Swagger UI](http://localhost:8080/api/payments/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/payments/v3/api-docs)
- Inventory: [Swagger UI](http://localhost:8080/api/inventory/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/inventory/v3/api-docs)
- Delivery: [Swagger UI](http://localhost:8080/api/delivery/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/delivery/v3/api-docs)
- Notification: [Swagger UI](http://localhost:8080/api/notifications/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/notifications/v3/api-docs)
- Review: [Swagger UI](http://localhost:8080/api/reviews/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/reviews/v3/api-docs)
- Search: [Swagger UI](http://localhost:8080/api/search/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/search/v3/api-docs)
- Recommendation: [Swagger UI](http://localhost:8080/api/recommendations/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/recommendations/v3/api-docs)
- Analytics: [Swagger UI](http://localhost:8080/api/analytics/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/analytics/v3/api-docs)
- Reporting: [Swagger UI](http://localhost:8080/api/reports/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/reports/v3/api-docs)
- Data Pipeline: [Swagger UI](http://localhost:8080/api/pipeline/swagger-ui/index.html) · [OpenAPI JSON](http://localhost:8080/api/pipeline/v3/api-docs)

Note: If a link returns 404, ensure the service includes Springdoc OpenAPI and exposes Swagger UI; otherwise, use the base route links or service README.

### 🧩 Component Architecture

<details>
  <summary>Component Architecture – Core, Advanced, Platform</summary>

```mermaid
graph LR
  subgraph Core
    US[User]
    AS[Auth]
    PR[Product]
    CT[Cart]
    OR[Order]
    PM[Payment]
  end
  subgraph Advanced
    IV[Inventory]
    DV[Delivery]
    NT[Notification]
    RV[Review]
    SR[Search]
    RC[Recommendation]
    AN[Analytics]
    RP[Reporting]
  end
  subgraph Platform
    GW[API Gateway]
    RG[Service Registry]
    CF[Config Server]
    MN[Monitoring]
    LG[Logging]
    AD[Audit]
    BK[Backup]
    SC[Scheduler]
    DP[Data Pipeline]
  end
  GW-->US
  GW-->PR
  GW-->CT
  CT-->IV
  OR-->PM
  OR-->IV
  OR-->DV
  PR-->SR
  CT-->RC
  OR-->NT
  DP-->AN
  PR-->DP
  OR-->DP
  PM-->DP
  AN-->RP
  US-->AD
  OR-->AD
  click US "http://localhost:8080/api/users/" "Open User API via Gateway"
  click AS "http://localhost:8080/api/auth/" "Open Auth API via Gateway"
  click PR "http://localhost:8080/api/products/" "Open Product API via Gateway"
  click CT "http://localhost:8080/api/cart/" "Open Cart API via Gateway"
  click OR "http://localhost:8080/api/orders/" "Open Order API via Gateway"
  click PM "http://localhost:8080/api/payments/" "Open Payment API via Gateway"
  click IV "http://localhost:8080/api/inventory/" "Open Inventory API via Gateway"
  click DV "http://localhost:8080/api/delivery/" "Open Delivery API via Gateway"
  click NT "http://localhost:8080/api/notifications/" "Open Notification API via Gateway"
  click RV "http://localhost:8080/api/reviews/" "Open Review API via Gateway"
  click SR "http://localhost:8080/api/search/" "Open Search API via Gateway"
  click RC "http://localhost:8080/api/recommendations/" "Open Recommendation API via Gateway"
  click AN "http://localhost:8080/api/analytics/" "Open Analytics API via Gateway"
  click RP "http://localhost:8080/api/reports/" "Open Reporting API via Gateway"
  click GW "api-gateway/README.md" "API Gateway README"
  click RG "service-registry/README.md" "Service Registry README"
  click CF "config-server/README.md" "Config Server README"
  click MN "monitoring-service/README.md" "Monitoring Service README"
  click LG "logging-service/README.md" "Logging Service README"
  click AD "audit-service/README.md" "Audit Service README"
  click BK "backup-service/README.md" "Backup Service README"
  click SC "scheduler-service/README.md" "Scheduler Service README"
  click DP "http://localhost:8080/api/pipeline/" "Open Data Pipeline API via Gateway"
```

### 🌐 Deployment Topology

<details>
  <summary>Deployment Topology – K8s Cluster/Namespace</summary>

```mermaid
graph TB
  subgraph Cluster
    subgraph Namespace: ecommerce
      GW((Gateway))
      RG((Eureka))
      CF((Config))
      US[(User)]
      AS[(Auth)]
      PR[(Product)]
      IV[(Inventory)]
      CT[(Cart)]
      OR[(Order)]
      PM[(Payment)]
      DV[(Delivery)]
      NT[(Notification)]
      SR[(Search)]
      RC[(Recommend)]
      AN[(Analytics)]
      RP[(Reporting)]
      AD[(Audit)]
      BK[(Backup)]
      SC[(Scheduler)]
      DP[(Data Pipeline)]
      MN[(Monitoring)]
      LG[(Logging)]
    end
  end
  GW-->US & PR & CT & OR & PM
  OR-->DV & NT
  PR-->SR & RC
  DP-->AN & RP
  US-->AD
```

</details>

### 🖼️ Illustrations

- High-level PNG/SVG diagrams can be found in `docs/images/` (add your exported diagrams here).
- All Mermaid diagrams above render on GitHub and most IDEs for instant visualization.

---

## 🚀 Quick Start Guide

### 📋 **Prerequisites**

- ☕ **Java 17** or higher
- 🔨 **Maven 3.8+**
- 🐳 **Docker & Docker Compose**
- 🐘 **PostgreSQL 14+**
- 🍃 **MongoDB 6.0+**
- 🔴 **Redis 7.0+**
- 📨 **Apache Kafka 3.5+**

### 🪟 Windows Quick Start (PowerShell)

```powershell
# Start infrastructure (databases, Kafka, etc.)
./start-infrastructure.ps1

# If you see "running scripts is disabled" error, enable scripts for current user:
Set-ExecutionPolicy -Scope CurrentUser RemoteSigned
```

### 🛠️ **Installation Steps**

#### 1️⃣ **Clone the Repository**
```bash
git clone https://github.com/your-org/ecommerce-microservices.git
cd ecommerce-microservices
```

#### 2️⃣ **Start Infrastructure Services**
```bash
# Start databases and message broker
docker-compose -f docker-compose-infra.yml up -d

# Verify services are running
docker-compose -f docker-compose-infra.yml ps
```

#### 3️⃣ **Build All Services**
```bash
# Clean and build all microservices
mvn clean install -DskipTests

# Or build with tests (recommended)
mvn clean install
```

#### 4️⃣ **Start Core Services**
```bash
# Start services in order
java -jar service-registry/target/service-registry-1.0.0.jar &
java -jar config-server/target/config-server-1.0.0.jar &
java -jar api-gateway/target/api-gateway-1.0.0.jar &
```

#### 5️⃣ **Start Business Services**
```bash
# Start all business services (they will auto-register with Eureka)
java -jar user-service/target/user-service-1.0.0.jar &
java -jar auth-service/target/auth-service-1.0.0.jar &
java -jar product-service/target/product-service-1.0.0.jar &
# ... continue for all services
```

#### 6️⃣ **Verify Installation**
```bash
# Check service registry
curl http://localhost:8761/eureka/apps

# Test API Gateway
curl http://localhost:8080/actuator/health

# Run E2E tests
java -cp . StandaloneE2EDemo
```

---

## ⚙️ Environment Variables & Profiles

- Spring profiles: `dev`, `docker`, `prod` (set via `SPRING_PROFILES_ACTIVE`)
- Common environment variables (sample):

```env
# Kafka
KAFKA_BOOTSTRAP_SERVERS=localhost:9092

# Postgres
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_USER=ecom
POSTGRES_PASSWORD=secret

# Mongo
MONGO_URI=mongodb://localhost:27017/ecommerce

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379

# Security
JWT_SECRET=change_me
```

Configure service-specific variables in each service's `application.yml` or via the Config Server.

---

## 📊 Monitoring & Observability

### 🔍 **Health Checks**
- **Service Registry**: http://localhost:8761
- **API Gateway Health**: http://localhost:8080/actuator/health
- **Individual Service Health**: http://localhost:{port}/actuator/health

### 📈 **Metrics & Monitoring**
- **Prometheus Metrics**: http://localhost:8080/actuator/prometheus
- **Grafana Dashboards**: http://localhost:3000
- **Application Metrics**: Custom business metrics via Micrometer

### 📋 **Logging**
- **Centralized Logging**: ELK Stack integration
- **Structured Logging**: JSON format with correlation IDs
- **Log Levels**: Configurable per service and environment

---

## 🔒 Security Features

### 🛡️ **Authentication & Authorization**
- **JWT Tokens**: Stateless authentication
- **Role-Based Access**: Fine-grained permissions
- **API Security**: Rate limiting and request validation
- **Data Encryption**: At rest and in transit

### 🔐 **Security Best Practices**
- **HTTPS Enforcement**: All external communications
- **Input Validation**: Comprehensive request validation
- **SQL Injection Protection**: Parameterized queries
- **CORS Configuration**: Proper cross-origin settings

---

## 🧪 Testing Strategy

### ✅ **Test Coverage**
- **Unit Tests**: 85%+ coverage for all services
- **Integration Tests**: End-to-end workflow validation
- **Contract Tests**: API contract verification
- **Performance Tests**: Load and stress testing

### 🔬 **Test Execution**
```bash
# Run all tests
mvn clean test

# Run integration tests
mvn clean verify

# Run E2E demonstration
java StandaloneE2EDemo
```

---

## 🐳 Docker & Kubernetes

### 🐳 **Docker Support**
- **Multi-stage Builds**: Optimized container images
- **Health Checks**: Container health monitoring
- **Environment Configs**: Environment-specific settings
- **Resource Limits**: Memory and CPU constraints

### ☸️ **Kubernetes Deployment**
```bash
# Deploy to Kubernetes
kubectl apply -f k8s/

# Check deployment status
kubectl get pods
kubectl get services
```

### 📊 **Production Metrics**
- **Response Time**: <200ms average
- **Throughput**: >10,000 requests/second
- **Availability**: 99.9% uptime
- **Scalability**: Auto-scaling based on demand

---

## 📚 API Documentation

### 🔗 **API Endpoints**
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8080/v3/api-docs
- **Postman Collection**: Available in `/docs/postman/`

### 📖 **Documentation**
- **Architecture Guide**: `/docs/Ecommerce-System-Architecture.md`
- **Deployment Guide**: `/docs/deployment.md`
- **API Reference**: `/docs/api-reference.md`
- **Troubleshooting**: `/docs/troubleshooting.md`

### 🧪 Smoke Test (Postman)
- Collection: `/docs/postman/CoreFlows.postman_collection.json`
- Environment: `/docs/postman/DevEnvironment.postman_environment.json`

Steps:
1. Import the environment, then the collection.
2. Select "Ecommerce Dev" environment.
3. Run in order: Add Item to Cart → Create Order → Confirm Order.
4. (Optional) Run Cancel Order to test rollback and inventory release.

---

## 🛠️ Troubleshooting

- **PowerShell execution policy (Windows):** If `profile.ps1 cannot be loaded` or `running scripts is disabled`, run:
  ```powershell
  Set-ExecutionPolicy -Scope CurrentUser RemoteSigned
  ```
- **Ports in use:** Services may fail if ports are occupied. Change ports in `application.yml` or stop conflicting processes.
- **Kafka topics:** Ensure Kafka is running from `docker-compose-infra.yml`. Topics are auto-created by producers when configured; see `docs/Ecommerce-System-Architecture.md` (Event Topics section).
- **Database initialization:**
  - Postgres schemas: `init-databases.sql`
  - Mongo seed & indexes: `mongo-init.js`
- **Service discovery not showing services:** Start order matters — launch `service-registry` → `config-server` → `api-gateway` before business services.

---

## 🤝 Contributing

We welcome contributions! Please read our [Contributing Guide](CONTRIBUTING.md) for details on:
- Code of conduct
- Development process
- Pull request guidelines
- Coding standards

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">

**🚀 Built with ❤️ for Production-Grade E-Commerce**

*Ready to scale from startup to enterprise*

![Stars](https://img.shields.io/github/stars/your-org/ecommerce-microservices?style=social)
![Forks](https://img.shields.io/github/forks/your-org/ecommerce-microservices?style=social)
![Issues](https://img.shields.io/github/issues/your-org/ecommerce-microservices)
![License](https://img.shields.io/github/license/your-org/ecommerce-microservices)

</div>
