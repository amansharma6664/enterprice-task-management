#!/bin/bash

echo "========================================="
echo "Enterprise Task Management System"
echo "========================================="
echo ""

# Check if MySQL is running
echo "Checking MySQL..."
if ! pgrep -x "mysqld" > /dev/null; then
    echo "⚠️  MySQL is not running. Please start MySQL first."
    echo "   sudo service mysql start"
    exit 1
fi
echo "✓ MySQL is running"
echo ""

# Backend setup
echo "Setting up Backend..."
cd backend

if [ ! -d "target" ]; then
    echo "Building backend for the first time..."
    mvn clean install -DskipTests
fi

echo "Starting Spring Boot application..."
mvn spring-boot:run &
BACKEND_PID=$!

echo "✓ Backend starting on http://localhost:8080"
echo ""

# Wait for backend to start
echo "Waiting for backend to be ready..."
sleep 15

# Frontend setup
echo "Setting up Frontend..."
cd ../frontend

if [ ! -d "node_modules" ]; then
    echo "Installing frontend dependencies..."
    npm install
fi

echo "Starting React development server..."
npm start &
FRONTEND_PID=$!

echo "✓ Frontend starting on http://localhost:3000"
echo ""

echo "========================================="
echo "Application is starting!"
echo "Backend:  http://localhost:8080/api"
echo "Frontend: http://localhost:3000"
echo "========================================="
echo ""
echo "Press Ctrl+C to stop all services"
echo ""

# Wait for Ctrl+C
trap "kill $BACKEND_PID $FRONTEND_PID; exit" INT
wait
