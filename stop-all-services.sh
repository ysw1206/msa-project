#!/bin/bash

echo "🛑 Stopping all microservices..."

# PID 파일들이 있는지 확인
if [ ! -d "pids" ]; then
    echo "❌ No PID files found. Services may not be running."
    exit 1
fi

# 각 서비스 종료
services=("gateway-service" "public-service" "user-service" "auth-service" "discovery-server" "config-server")

for service in "${services[@]}"; do
    if [ -f "pids/$service.pid" ]; then
        PID=$(cat "pids/$service.pid")
        if kill -0 "$PID" 2>/dev/null; then
            echo "🔄 Stopping $service (PID: $PID)..."
            kill "$PID"
            sleep 2
            # 강제 종료가 필요한 경우
            if kill -0 "$PID" 2>/dev/null; then
                echo "⚠️  Force killing $service..."
                kill -9 "$PID"
            fi
        else
            echo "⚠️  $service is not running (PID: $PID)"
        fi
        rm -f "pids/$service.pid"
    else
        echo "❌ No PID file for $service"
    fi
done

# PID 디렉토리 정리
rmdir pids 2>/dev/null

echo ""
echo "✅ All services stopped!"
echo "📝 Log files are still available in ./logs/ directory" 