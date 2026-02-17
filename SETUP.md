# Quick Setup Guide

## Prerequisites Installation

### 1. Install Java 17

- **Windows/Mac**: Download from https://adoptium.net/
- **Linux**:
  ```bash
  sudo apt update
  sudo apt install openjdk-17-jdk
  ```

### 2. Install Maven

- **Windows**: Download from https://maven.apache.org/download.cgi
- **Mac**: `brew install maven`
- **Linux**: `sudo apt install maven`

### 3. Install MySQL

- **Windows**: Download from https://dev.mysql.com/downloads/installer/
- **Mac**: `brew install mysql`
- **Linux**:
  ```bash
  sudo apt install mysql-server
  sudo systemctl start mysql
  ```

### 4. Install Node.js

- Download from https://nodejs.org/ (LTS version)
- Or use nvm: `nvm install --lts`

## Database Setup

1. Start MySQL service
2. Login to MySQL:

   ```bash
   mysql -u root -p
   ```
3. Create database and user:

   ```sql
   CREATE DATABASE task_management_db;
   CREATE USER 'taskuser'@'localhost' IDENTIFIED BY 'taskpass123';
   GRANT ALL PRIVILEGES ON task_management_db.* TO 'taskuser'@'localhost';
   FLUSH PRIVILEGES;
   EXIT;
   ```
4. Update `backend/src/main/resources/application.properties`:

   ```properties
   spring.datasource.username=taskuser
   spring.datasource.password=taskpass123
   ```

## Running the Application

### Option 1: Using Startup Scripts (Recommended)

**Linux/Mac:**

```bash
chmod +x start.sh
./start.sh
```

**Windows:**

```cmd
start.bat
```

### Option 2: Manual Start

**Terminal 1 - Backend:**

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

**Terminal 2 - Frontend:**

```bash
cd frontend
npm install
npm start
```

## First Time Use

1. Open browser to `http://localhost:3000`
2. Click "Register here" to create an account
3. Fill in your details and register
4. Login with your credentials
5. Start creating tasks!

## Creating an Admin User

Run this SQL command:

```sql
USE task_management_db;

INSERT INTO users (username, email, password, first_name, last_name, role, active, created_at, updated_at)
VALUES ('admin', 'admin@example.com', 
        '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG',
        'Admin', 'User', 'ADMIN', true, NOW(), NOW());
```

Login with:

- Username: `admin`
- Password: `admin123`

## Common Issues

### Port 8080 already in use

Change backend port in `application.properties`:

```properties
server.port=8081
```

### Port 3000 already in use

Frontend will prompt to use a different port (e.g., 3001)

### Database connection failed

- Ensure MySQL is running: `sudo systemctl status mysql`
- Check credentials in application.properties
- Verify database exists: `SHOW DATABASES;`

### Maven build fails

- Check Java version: `java -version` (should be 17+)
- Clear Maven cache: `mvn clean`

## Testing the Application

### Backend Tests

```bash
cd backend
mvn test
```

### API Testing with curl

**Register:**

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "firstName": "Test",
    "lastName": "User"
  }'
```

**Login:**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "password123"
  }'
```

## Next Steps

1. Explore the dashboard
2. Create your first task
3. Create a team
4. Assign tasks to team members
5. Track task progress

For detailed documentation, see README.md
