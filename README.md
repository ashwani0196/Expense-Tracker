# Expense Tracker — Ashwani's Module (Login & Registration)

This starter implements your assigned **Login & Registration** module and a basic MySQL connection.

## Requirements
- JDK 11 or newer
- Maven
- MySQL Server
- Apache Tomcat 9 (this project uses `javax.servlet`, so use Tomcat 9 rather than Tomcat 10+)

## 1. Create the database
Open MySQL Workbench or the MySQL command line and run `database.sql`.

## 2. Set your database credentials
Open `src/main/java/com/expensetracker/DBConnection.java` and replace `YOUR_MYSQL_PASSWORD` with your local MySQL password. Keep this local password private; do not upload it to a public repository.

## 3. Build
From this project folder, run:
```bash
mvn clean package
```
This creates `target/expense-tracker.war`.

## 4. Deploy
Copy `target/expense-tracker.war` into Tomcat 9's `webapps` folder, start Tomcat, then open:
`http://localhost:8080/expense-tracker/`

## Included
- `index.html`: links to login and registration
- `register.html` + `RegisterServlet.java`: creates an account
- `login.html` + `LoginServlet.java`: checks credentials
- `DBConnection.java`: JDBC connection
- `database.sql`: users table

## Important
This is a learning starter, not production-ready authentication. It hashes passwords using Java's PBKDF2 API, but a real deployment should also add HTTPS, rate limiting, session protections, CSRF protection, password reset, and stronger validation. Never use a real account password in a college demo.
