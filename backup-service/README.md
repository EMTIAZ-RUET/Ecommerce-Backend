# Backup Service

- Purpose: Automated backups/restores of data stores.
- See: `../docs/Ecommerce-System-Architecture.md`

## Run
```bash
mvn spring-boot:run -pl backup-service
```

## Notes
- Configure retention and destinations (S3-compatible) in config.

## Health
- `GET /actuator/health`
