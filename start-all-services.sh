#!/bin/bash

echo "🚀 Starting all microservices..."

# 서비스 시작 순서 (의존성 고려)
echo "📋 Starting services in dependency order..."

# 1. Config Server (다른 서비스들이 설정을 가져가기 위해 먼저 시작)
echo "🔧 Starting Config Server..."
./gradlew :config-server:bootRun > logs/config-server.log 2>&1 &
CONFIG_PID=$!
echo "Config Server PID: $CONFIG_PID"
sleep 10

# 2. Discovery Server (서비스 등록을 위해)
echo "🔍 Starting Discovery Server..."
./gradlew :discovery-server:bootRun > logs/discovery-server.log 2>&1 &
DISCOVERY_PID=$!
echo "Discovery Server PID: $DISCOVERY_PID"
sleep 10

# 3. Auth Service
echo "🔐 Starting Auth Service..."
./gradlew :auth-service:bootRun > logs/auth-service.log 2>&1 &
AUTH_PID=$!
echo "Auth Service PID: $AUTH_PID"
sleep 5

# 4. User Service
echo "👤 Starting User Service..."
./gradlew :user-service:bootRun > logs/user-service.log 2>&1 &
USER_PID=$!
echo "User Service PID: $USER_PID"
sleep 5

# 5. Public Service
echo "🌐 Starting Public Service..."
./gradlew :public-service:bootRun > logs/public-service.log 2>&1 &
PUBLIC_PID=$!
echo "Public Service PID: $PUBLIC_PID"
sleep 5

# 6. Gateway Service (마지막에 시작)
echo "🚪 Starting Gateway Service..."
./gradlew :gateway-service:bootRun > logs/gateway-service.log 2>&1 &
GATEWAY_PID=$!
echo "Gateway Service PID: $GATEWAY_PID"

# PID 파일에 저장 (종료시 사용)
mkdir -p pids
echo $CONFIG_PID > pids/config-server.pid
echo $DISCOVERY_PID > pids/discovery-server.pid
echo $AUTH_PID > pids/auth-service.pid
echo $USER_PID > pids/user-service.pid
echo $PUBLIC_PID > pids/public-service.pid
echo $GATEWAY_PID > pids/gateway-service.pid

echo ""
echo "✅ All services started!"
echo ""
echo "📊 Service Status:"
echo "Config Server:    http://localhost:8888"
echo "Discovery Server: http://localhost:8761"
echo "Auth Service:     http://localhost:8081"
echo "User Service:     http://localhost:8082"
echo "Public Service:   http://localhost:8083"
echo "Gateway Service:  http://localhost:8080"
echo ""
echo "📝 Logs are saved in ./logs/ directory"
echo "🛑 To stop all services, run: ./stop-all-services.sh"
echo ""
echo "⏳ Waiting for all services to be ready..."
sleep 30
echo "🎉 All services should be ready now!" 