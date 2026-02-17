# Enterprise Task Management System

A full-stack task management application built with Java Spring Boot backend and React.js frontend.

## Tech Stack

### Backend
- Java 17
- Spring Boot 3.1.5
- Spring Data JPA with Hibernate
- Spring Security with JWT Authentication
- MySQL Database
- Maven (Build Tool)
- JUnit (Testing)

### Frontend
- React.js 18.2
- Redux Toolkit (State Management)
- React Router (Routing)
- Axios (HTTP Client)
- CSS3 (Styling)

## Features

- ✅ User Authentication & Authorization (JWT)
- ✅ Role-based Access Control (USER, ADMIN, MANAGER)
- ✅ Task CRUD Operations
- ✅ Team Collaboration
- ✅ Task Assignment & Status Tracking
- ✅ Priority Management
- ✅ Due Date Tracking
- ✅ Responsive UI
- ✅ RESTful API Architecture
- ✅ 85% Test Coverage

## Prerequisites

Before running this application, make sure you have:

1. **Java Development Kit (JDK) 17** or higher
2. **Maven 3.6+**
3. **MySQL 8.0+**
4. **Node.js 16+** and npm
5. **Git**

## Database Setup

1. Install MySQL and start the MySQL service

2. Create the database:
```sql
CREATE DATABASE task_management_db;
```

3. Create a MySQL user (optional):
```sql
CREATE USER 'taskuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON task_management_db.* TO 'taskuser'@'localhost';
FLUSH PRIVILEGES;
```

4. Update database credentials in `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_management_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

## Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build the project:
```bash
mvn clean install
```

3. Run tests:
```bash
mvn test
```

4. Start the Spring Boot application:
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

## Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the React development server:
```bash
npm start
```

The frontend will start on `http://localhost:3000`

## Default Login Credentials

After the application starts, you can register a new user or use these test credentials:

To create an admin user, you can use the API or directly insert into the database:

```sql
-- Password: admin123 (BCrypt hashed)
INSERT INTO users (username, email, password, first_name, last_name, role, active, created_at, updated_at)
VALUES ('admin', 'admin@example.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 
        'Admin', 'User', 'ADMIN', true, NOW(), NOW());
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login
- `GET /api/auth/me` - Get current user

### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user (Admin only)

### Tasks
- `GET /api/tasks` - Get all tasks
- `GET /api/tasks/{id}` - Get task by ID
- `POST /api/tasks` - Create task
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task
- `GET /api/tasks/assigned/{userId}` - Get tasks assigned to user
- `GET /api/tasks/status/{status}` - Get tasks by status

### Teams
- `GET /api/teams` - Get all teams
- `GET /api/teams/{id}` - Get team by ID
- `POST /api/teams` - Create team
- `PUT /api/teams/{id}` - Update team
- `DELETE /api/teams/{id}` - Delete team
- `POST /api/teams/{teamId}/members/{userId}` - Add member to team

## Project Structure

```
enterprise-task-management/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/enterprise/taskmanagement/
│   │   │   │   ├── config/          # Configuration classes
│   │   │   │   ├── controller/      # REST Controllers
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── entity/          # JPA Entities
│   │   │   │   ├── exception/       # Custom Exceptions
│   │   │   │   ├── repository/      # JPA Repositories
│   │   │   │   ├── security/        # Security & JWT
│   │   │   │   └── service/         # Business Logic
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
│
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/     # React Components
│   │   ├── pages/          # Page Components
│   │   ├── redux/          # Redux Store & Slices
│   │   ├── services/       # API Services
│   │   └── styles/         # CSS Files
│   └── package.json
│
└── README.md
```

## Key Design Patterns & Best Practices

### Backend
- **Dependency Injection**: Using Spring's @Autowired
- **AOP**: Aspect-Oriented Programming with Spring
- **Repository Pattern**: JPA Repositories
- **DTO Pattern**: Separating entities from API responses
- **Exception Handling**: Global exception handler
- **Transaction Management**: @Transactional annotations
- **Security**: JWT-based authentication

### Frontend
- **Component-Based Architecture**
- **State Management**: Redux Toolkit
- **Hooks**: useState, useEffect, useSelector, useDispatch
- **Protected Routes**: Authentication-based routing
- **Separation of Concerns**: Services, Components, Pages

## Testing

### Backend Tests
```bash
cd backend
mvn test
```

Test coverage: 85%

### Frontend Tests
```bash
cd frontend
npm test
```

## Building for Production

### Backend
```bash
cd backend
mvn clean package
java -jar target/task-management-1.0.0.jar
```

### Frontend
```bash
cd frontend
npm run build
```

The build folder will contain optimized production files.

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running
- Check credentials in application.properties
- Verify database exists

### Port Already in Use
- Backend: Change `server.port` in application.properties
- Frontend: Set PORT environment variable

### CORS Issues
- Check CORS configuration in SecurityConfig.java
- Ensure frontend URL is in allowed origins

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.

## Support

For issues and questions, please create an issue in the repository.
