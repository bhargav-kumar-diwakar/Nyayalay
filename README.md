Nyayalay — Court Case Management System

A RESTful backend application built with Spring Boot for managing court cases, hearings, parties, and legal documents. Designed with a clean 3-layer MVC architecture following industry standard backend development practices.

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Core programming language |
| Spring Boot 3.2 | Backend framework |
| Spring Data JPA | Database ORM layer |
| Spring Boot Validation | Request validation |
| MySQL | Relational database |
| Lombok | Boilerplate reduction |
| Maven | Build and dependency management |

---

## Project Structure

```
src/main/java/com/ccms/
├── controller/              → REST API endpoints
├── service/                 → Business logic interfaces
│   └── impl/                → Business logic implementations
├── repository/              → Database access layer
├── model/
│   └── entity/              → JPA entities
├── dto/                     → Data Transfer Objects
└── NyayalayApplication.java → Application entry point
```

---

## Getting Started

### Prerequisites

Make sure you have the following installed on your machine:

- Java 17 or above
- Maven 3.6 or above
- MySQL 8.0 or above
- IntelliJ IDEA (recommended) or any Java IDE

### Step 1 — Clone the Repository

```bash
git clone https://github.com/bhargav-kumar-diwakar/nyayalay.git
cd nyayalay
```

### Step 2 — Create the Database

Open MySQL Workbench or your MySQL terminal and run:

```sql
CREATE DATABASE nyayalay_db;
```

### Step 3 — Configure application.properties

Open `src/main/resources/application.properties` and update your credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nyayalay_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

### Step 4 — Run the Application

```bash
mvn spring-boot:run
```

The server starts at:
```
http://localhost:8080
```

> All database tables are created automatically on first run via `spring.jpa.hibernate.ddl-auto=update`
