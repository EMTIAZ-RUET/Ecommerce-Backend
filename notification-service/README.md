# Notification Service

- Purpose: Send transactional notifications (email/SMS/push) triggered by domain events.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl notification-service
```

## Events
- Consumes: order/payment/delivery/user events (via Kafka)
- Sends via providers (e.g., SendGrid/Twilio) configured in application properties

## Notes
- Template-based rendering recommended; retries on transient failures.
- Expose minimal health endpoint: `GET /actuator/health`.
