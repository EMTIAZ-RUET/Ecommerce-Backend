#!/bin/bash

# Build all services
mvn clean package -DskipTests

# Build Docker images
services=("api-gateway" "service-registry" "config-server" "product-service" "order-service" "inventory-service" "payment-service" "user-service")

for service in "${services[@]}"
do
  docker build -t "ironsoftware/$service:latest" "./$service"
done

# Start infrastructure services
docker-compose up -d zookeeper kafka postgres mongodb redis elasticsearch kibana logstash prometheus grafana zipkin

# Wait for infrastructure services to be ready
echo "Waiting for infrastructure services to be ready..."
sleep 30

# Apply Kubernetes configurations
kubectl apply -f k8s/config.yml
kubectl apply -f k8s/api-gateway.yml
kubectl apply -f k8s/product-service.yml
# Add other service configurations as needed

echo "Deployment completed. Check service status with: kubectl get pods"
