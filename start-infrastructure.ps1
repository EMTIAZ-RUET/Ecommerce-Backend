# E-Commerce Infrastructure Startup Script
# This script starts all infrastructure services using Docker

Write-Host "🚀 Starting E-Commerce Infrastructure Services..." -ForegroundColor Green

# Check if Docker is running
try {
    docker version | Out-Null
    Write-Host "✅ Docker is running" -ForegroundColor Green
} catch {
    Write-Host "❌ Docker is not running. Please start Docker Desktop first." -ForegroundColor Red
    exit 1
}

# Create network if it doesn't exist
Write-Host "🌐 Creating Docker network..." -ForegroundColor Yellow
docker network create ecommerce-network 2>$null

# Start infrastructure services
Write-Host "🐳 Starting infrastructure services..." -ForegroundColor Yellow
docker-compose -f docker-compose-infra.yml up -d

# Wait for services to be healthy
Write-Host "⏳ Waiting for services to be ready..." -ForegroundColor Yellow

$services = @(
    @{name="PostgreSQL"; container="ecommerce-postgres"; port=5432},
    @{name="MongoDB"; container="ecommerce-mongodb"; port=27017},
    @{name="Redis"; container="ecommerce-redis"; port=6379},
    @{name="Kafka"; container="ecommerce-kafka"; port=9092},
    @{name="Elasticsearch"; container="ecommerce-elasticsearch"; port=9200},
    @{name="Prometheus"; container="ecommerce-prometheus"; port=9090},
    @{name="Grafana"; container="ecommerce-grafana"; port=3000}
)

foreach ($service in $services) {
    $maxAttempts = 30
    $attempt = 0
    
    do {
        $attempt++
        try {
            $health = docker inspect --format='{{.State.Health.Status}}' $service.container 2>$null
            if ($health -eq "healthy" -or $health -eq "") {
                # For services without health check, check if container is running
                $status = docker inspect --format='{{.State.Status}}' $service.container 2>$null
                if ($status -eq "running") {
                    Write-Host "✅ $($service.name) is ready" -ForegroundColor Green
                    break
                }
            }
        } catch {
            # Container might not exist yet
        }
        
        if ($attempt -eq $maxAttempts) {
            Write-Host "⚠️  $($service.name) is taking longer than expected" -ForegroundColor Yellow
            break
        }
        
        Start-Sleep -Seconds 2
    } while ($attempt -lt $maxAttempts)
}

Write-Host ""
Write-Host "🎉 Infrastructure services are starting up!" -ForegroundColor Green
Write-Host ""
Write-Host "📊 Access URLs:" -ForegroundColor Cyan
Write-Host "  • PostgreSQL:     localhost:5432 (postgres/postgres)" -ForegroundColor White
Write-Host "  • MongoDB:        localhost:27017 (admin/admin123)" -ForegroundColor White
Write-Host "  • Redis:          localhost:6379 (password: redis123)" -ForegroundColor White
Write-Host "  • Kafka:          localhost:9092" -ForegroundColor White
Write-Host "  • Kafka UI:       http://localhost:8089" -ForegroundColor White
Write-Host "  • Elasticsearch:  http://localhost:9200" -ForegroundColor White
Write-Host "  • Kibana:         http://localhost:5601" -ForegroundColor White
Write-Host "  • Prometheus:     http://localhost:9090" -ForegroundColor White
Write-Host "  • Grafana:        http://localhost:3000 (admin/admin123)" -ForegroundColor White
Write-Host "  • Zipkin:         http://localhost:9411" -ForegroundColor White
Write-Host "  • Jaeger:         http://localhost:16686" -ForegroundColor White
Write-Host "  • Keycloak:       http://localhost:8443 (admin/admin123)" -ForegroundColor White
Write-Host "  • PgAdmin:        http://localhost:5050 (admin@ecommerce.com/admin123)" -ForegroundColor White
Write-Host "  • Mongo Express:  http://localhost:8082 (admin/admin123)" -ForegroundColor White
Write-Host "  • Redis Commander: http://localhost:8083" -ForegroundColor White
Write-Host "  • Airflow:        http://localhost:8093" -ForegroundColor White
Write-Host ""
Write-Host "🔧 To stop all services: docker-compose -f docker-compose-infra.yml down" -ForegroundColor Yellow
Write-Host "📝 To view logs: docker-compose -f docker-compose-infra.yml logs -f [service-name]" -ForegroundColor Yellow
