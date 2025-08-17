-- Initialize databases for microservices
CREATE DATABASE IF NOT EXISTS user_service;
CREATE DATABASE IF NOT EXISTS auth_service;
CREATE DATABASE IF NOT EXISTS order_service;
CREATE DATABASE IF NOT EXISTS payment_service;
CREATE DATABASE IF NOT EXISTS inventory_service;
CREATE DATABASE IF NOT EXISTS delivery_service;
CREATE DATABASE IF NOT EXISTS review_service;
CREATE DATABASE IF NOT EXISTS audit_service;
CREATE DATABASE IF NOT EXISTS reporting_service;
CREATE DATABASE IF NOT EXISTS keycloak;
CREATE DATABASE IF NOT EXISTS airflow;

-- Grant permissions
GRANT ALL PRIVILEGES ON DATABASE user_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE auth_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE order_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE payment_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE inventory_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE delivery_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE review_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE audit_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE reporting_service TO postgres;
GRANT ALL PRIVILEGES ON DATABASE keycloak TO postgres;
GRANT ALL PRIVILEGES ON DATABASE airflow TO postgres;
