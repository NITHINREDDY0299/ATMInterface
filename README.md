# Bank Management System

A web-based Bank Management System built with **Java Servlets**, **JSP**, and **Maven** as per the Java Major Project requirements.

## Project Features

- **Three Login Portals**
  - **Customer Portal** — Register with personal details, login, deposit, withdraw, view balance
  - **Employee Portal** — Login with Employee ID, name, and position
  - **Admin Portal** — View all customer and employee profiles

- **Banking Operations**
  - Deposit money with validation (Rs. 100 minimum, Rs. 50,000 maximum per transaction)
  - Withdraw money only when sufficient balance is available
  - Automatic monthly interest calculation (0.5% per month) applied on login
  - Complete transaction history

## Prerequisites

- Java JDK 11 or higher
- Apache Maven 3.6+

## How to Run

1. Open a terminal and navigate to the project folder:
   ```
   cd BankManagementSystem
   ```

2. Build the project:
   ```
   mvn clean package
   ```

3. Run the application using embedded Jetty server:
   ```
   mvn jetty:run
   ```

4. Open your browser and go to:
   ```
   http://localhost:8080
   ```

## Demo Login Credentials

### Admin
| Field    | Value     |
|----------|-----------|
| Username | admin     |
| Password | admin123  |

### Employee 1
| Field       | Value          |
|-------------|----------------|
| Employee ID | EMP101         |
| Name        | John Smith     |
| Position    | Branch Manager |
| Password    | emp123         |

### Employee 2
| Field       | Value          |
|-------------|----------------|
| Employee ID | EMP102         |
| Name        | Sarah Johnson  |
| Position    | Cashier        |
| Password    | emp123         |

### Customer
Register a new account from the Customer Portal registration page.

## Project Structure

```
BankManagementSystem/
├── pom.xml
├── src/main/java/com/bank/
│   ├── model/
│   │   ├── Customer.java
│   │   ├── Employee.java
│   │   └── Transaction.java
│   ├── service/
│   │   └── BankService.java
│   ├── servlet/
│   │   ├── LoginServlet.java
│   │   ├── RegisterServlet.java
│   │   ├── CustomerTransactionServlet.java
│   │   └── LogoutServlet.java
│   └── util/
│       └── BankConstants.java
└── src/main/webapp/
    ├── index.jsp
    ├── css/style.css
    ├── customer/
    ├── employee/
    └── admin/
```

## Deployment on Tomcat (Alternative)

1. Build the WAR file: `mvn clean package`
2. Copy `target/bank-management-system.war` to Tomcat's `webapps` folder
3. Start Tomcat and access `http://localhost:8080/bank-management-system`

## Eclipse IDE Setup

1. Import as **Existing Maven Project**
2. Right-click project → Run As → Run on Server (Tomcat 9+)
3. Access the application at the URL shown in the console

## Submission

As per project requirements:
1. Copy all source code into a Word document
2. Convert the Word document to PDF
3. Upload the PDF to the submission form
