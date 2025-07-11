# ⚽ Football Management API

A Spring Boot RESTful API for managing football teams, players, and coaches. Built with Java, JPA, ModelMapper, and MySQL.

---

## 📦 Features

- CRUD operations for:
  - Players
  - Teams
  - Coaches
- Assign players to teams
- Assign coaches to teams
- Filter players by position
- RESTful architecture
- DTOs and ModelMapper for clean separation
- MySQL database integration

---

## 🚀 Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- ModelMapper
- MySQL
- Postman (for testing)
- Maven

---

## 🔧 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/football-management-api.git
cd football-management-api

2. Configure the database
Update src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/football_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Make sure football_db exists in MySQL or set ddl-auto=create to generate it automatically.

3. Build & Run
On macOS/Linux:
./mvnw clean package
./mvnw spring-boot:run

On Windows:
mvnw.cmd clean package
mvnw.cmd spring-boot:run

🧪 API Testing (Postman)
Use Postman to test the endpoints. Example JSON bodies and endpoints:

✅ Create a Team
POST /teams

json

{
  "name": "Arsenal",
  "country": "England"
}
✅ Hire a Coach
POST /coaches

json

{
  "name": "Mikel Arteta",
  "nationality": "Spain",
  "experienceInYears": 6,
  "teamId": 1
}
✅ Create a Player
POST /players

json

{
  "name": "Bukayo Saka",
  "age": 22,
  "nationality": "England",
  "position": "Winger",
  "teamId": 1
}

📁 Project Structure
arduino
Copy
Edit
├── controller/
├── model/
├── dto/
├── repository/
├── mapper/
├── config/
├── FootballManagementApplication.java
💡 Future Improvements
Authentication and Authorization (JWT)

Unit and integration tests

Swagger API documentation

Frontend client
