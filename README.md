# Digital Wallet – Backend (Spring Boot)

##  Overview
This is the backend service for the Digital Wallet application built using Spring Boot.
It handles wallet balance, transactions, and transaction history.

---

##  Architecture
- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- REST APIs
- Embedded Tomcat

Frontend (React) communicates with this backend via REST APIs.

---

##  Project Structure
src/main/java/com/wallet
├── controller
├── service
├── repository
├── entity
└── config


---

##  Setup Instructions

### 1️ Prerequisites
- Java 17+
- Maven
- MySQL
- Git

### 2️ Database Setup
Create database in MySQL:
```sql

### 3️ Update application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/wallet_db
spring.datasource.username=root
spring.datasource.password=your_password

###4️ Run Application
mvn spring-boot:run


Backend runs on:

http://localhost:8080

### API Endpoints

GET /wallet/balance?userId=1

POST /transaction

GET /transactions/history?userId=1

## CORS

CORS is configured to allow requests from:

http://localhost:3000

 Status

Backend is fully functional and integrated with frontend.


---


