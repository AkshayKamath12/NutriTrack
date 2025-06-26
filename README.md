# Revive – Nutrition-Driven Wellness 

Revive is a React Native + Spring Boot app that helps users improve their overall wellness by analyzing nutrition label data from their meals. It provides personalized insights into categories like sleep, weight loss, satiety, and mental health.

---

## Tech Stack

- Java 21
- Spring Boot 3.5
- PostgreSQL
- Maven
- JPA (Hibernate)
- Spring Security (JWT auth)
- Lombok

---

## Getting Started

Follow these steps to set up and run the backend.

---

### Prerequisites

- [Java 21](https://adoptium.net/en-GB/) (Ensure `java -version` outputs version 21)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (Community Edition is fine)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Maven](https://maven.apache.org/download.cgi) (optional, IntelliJ can handle Maven)

---

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/Revive.git
cd Revive/app/server
```

---

### 2. Set Up PostgreSQL

1. **Install PostgreSQL** and start the service.
2. **Create a database and user:**

   ```sql
   CREATE DATABASE nutritrack;
   CREATE USER banana WITH PASSWORD 'bread';
   GRANT ALL PRIVILEGES ON DATABASE nutritrack TO banana;
   ```

   *(You can change the username and password, but update the config files accordingly.)*

---

### 3. Configure `application.properties`

Edit `src/main/resources/application.properties` to match your PostgreSQL setup:

```properties
spring.application.name=app
spring.datasource.url=jdbc:postgresql://localhost:5432/nutritrack
spring.datasource.username= your db username here
spring.datasource.password= your db password here

spring.jpa.hibernate.ddl-auto=update
logging.level.org.springframework.security=DEBUG

spring.config.import=optional:file:.env[.properties]
jwt.secret=${JWT_SECRET}
jwt.duration=${JWT_DURATION}
```

---

### 4. Create a `.env` File

At the root of the `app/server` directory, create a `.env` file with your JWT secret and duration:

```env
JWT_SECRET="examplesecret" (put your secret here in quotation marks like this)
JWT_DURATION=3600000 (or whatever you want)
```

---

### 5. Open and Run in IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Select **Open** and choose the `app/server` folder.
3. Let IntelliJ import the Maven project and download dependencies.
4. Click the green arrow to run the backend server

---

### 6. API Usage

- The backend will start on `http://localhost:8080` by default.
- Use Postman or your frontend to interact with the API.

---

## Troubleshooting

- If you get database connection errors, double-check your PostgreSQL credentials and that the database is running.
- If you get errors about missing environment variables, ensure your `.env` file is present and formatted correctly.
- For Lombok issues, make sure the Lombok plugin is enabled in IntelliJ.

---

## Example Directory Structure

```
Revive/
  app/
    server/
      src/
      .env
      pom.xml
      ...
```