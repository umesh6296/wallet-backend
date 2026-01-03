# Digital Wallet – Backend (Spring Boot, Kafka, WebSocket)

## Overview
This backend service powers the Digital Wallet application.
It handles wallet transactions, asynchronous processing using Kafka,
persistent storage using MySQL, and real-time transaction status updates
using WebSocket.

The system is designed using an event-driven architecture to ensure
scalability and reliability.

---

## Problems Covered

### Problem 1: Digital Wallet (Event-Driven Architecture)
- Wallet credit/debit transactions
- Kafka-based asynchronous processing
- Transaction history storage
- Wallet balance updates

### Problem 2: Scheduled Transaction & Status Monitoring
- Transactions created with `PENDING` status
- Kafka consumer processes transactions asynchronously
- Status updated to `SUCCESS` or `FAILURE`
- Real-time status pushed to frontend using WebSocket

---

## Architecture

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Apache Kafka
- WebSocket (STOMP)
- Embedded Tomcat

### Flow
1. Frontend sends transaction request
2. Backend stores transaction as `PENDING`
3. Kafka event is published
4. Kafka consumer processes transaction asynchronously
5. Status updated to `SUCCESS / FAILURE`
6. WebSocket notifies frontend in real time

---

## Project Structure

src/main/java/com/wallet
├── controller
├── service
├── repository
├── entity
├── kafka
├── websocket
└── config

yaml
Copy code

---

## Setup Instructions

### 1️⃣ Prerequisites
- Java 17+
- Maven
- MySQL
- Kafka & Zookeeper

---

### 2️⃣ Database Setup

```sql
CREATE DATABASE wallet_db;
Update application.properties:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/wallet_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3️⃣ Kafka Setup
Start Zookeeper:

bash
Copy code
zookeeper-server-start.bat config/zookeeper.properties
Start Kafka:

bash
Copy code
kafka-server-start.bat config/server.properties
Create topic:

bash
Copy code
kafka-topics.bat --create --topic transaction-events --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
4️⃣ Run Backend
bash
Copy code
mvn spring-boot:run
Backend runs on:

arduino
Copy code
http://localhost:8080
API Endpoints
Method	Endpoint
GET	/wallet/balance?userId=1
POST	/api/transaction
GET	/transactions/history?userId=1
WebSocket	/ws
Topic	/topic/transaction-status

CORS
CORS is enabled to allow requests from:

arduino
Copy code
http://localhost:3000
Design Decisions
Event-driven architecture using Kafka for scalability

Asynchronous transaction processing

Enum-based transaction status (PENDING, SUCCESS, FAILURE)

WebSocket for real-time frontend updates

Layered architecture for maintainability

MySQL for persistent storage

Status
✅ Backend is fully functional
✅ Integrated with Kafka and WebSocket
✅ Ready for production-scale extension
