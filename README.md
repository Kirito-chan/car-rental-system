# Spring Boot Project Setup

This document provides instructions for setting up and running the Spring Boot project with PostgreSQL and Liquibase for
database version control.

### Prerequisites

Before you begin, ensure that you have the following installed:

- **Java 21**: [Download Java](https://adoptopenjdk.net/) or directly from IntelliJ IDEA in Project Structure >
  Project > SDK > Download JDK...
- **Maven**: [Download Maven](https://maven.apache.org/download.cgi)
- **PostgreSQL**: [Download PostgreSQL](https://www.postgresql.org/download/). Used latest v17, but lower version should
  also work.
- **Liquibase** (Optional, for running database migrations
  manually): [Download Liquibase](https://www.liquibase.org/download)

### 1. Clone the Repository

If you haven't already cloned the repository, clone it using the following command:

```bash
git clone https://github.com/Kirito-chan/car-rental-system
```

### 2. Configure PostgreSQL Database

Before running the application, ensure that PostgreSQL is installed and running on your local machine.
Create a new database in PostgreSQL for the application. I used port 5432 and database name 'carrentaldb' as seen in
application properties, but can be configured freely.
If different, change application.properties for datasource to match your local PostgreSQL.
The most important are:

- Database URL: jdbc:postgresql://localhost:5432/carrentaldb
- Username: your_username
- Password: your_password

### 3. Install Project Dependencies

Navigate to the root of your Spring Boot project and install the project dependencies using Maven:

```bash
mvn clean install
```

### Run the Spring Boot Application

To start the Spring Boot application, run either the play button for class CarRentalSystemApplication in Intellij IDEA
or run the following command from the root of the project:

```bash
mvn spring-boot:run
```