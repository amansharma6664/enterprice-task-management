@echo off
echo =========================================
echo Enterprise Task Management System
echo =========================================
echo.

echo Setting up Backend...
cd backend
if not exist "target" (
    echo Building backend for the first time...
    call mvn clean install -DskipTests
)

echo Starting Spring Boot application...
start "Backend Server" cmd /k mvn spring-boot:run

echo Backend starting on http://localhost:8080
echo.

timeout /t 15 /nobreak

echo Setting up Frontend...
cd ..\frontend
if not exist "node_modules" (
    echo Installing frontend dependencies...
    call npm install
)

echo Starting React development server...
start "Frontend Server" cmd /k npm start

echo Frontend starting on http://localhost:3000
echo.

echo =========================================
echo Application is starting!
echo Backend:  http://localhost:8080/api
echo Frontend: http://localhost:3000
echo =========================================
echo.
echo Press any key to exit...
pause
