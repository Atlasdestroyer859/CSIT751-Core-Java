# CSIT751 – Core Java

A collection of practical assignments and Java applications developed for **CSIT751 – Core Java**.

## Student Details

| Field | Details |
|---|---|
| **Name** | Aditya Pathak |
| **Roll Number** | A010202626025 |
| **Program** | MCA (Generative AI) |

## Contents

### Module 1 — Java Fundamentals

Ten standalone programs covering identifiers, data types, arithmetic, decision-making, loops, arrays, and basic calculations.

### Module 2 — Object-Oriented Programming

Fifteen programs demonstrating object-oriented concepts through systems such as flight booking, payroll, hospital management, banking, hotel reservations, and more.

### Module 3 — Exception Handling and Multithreading

Java applications covering validation, transactions, ticket booking, ATM withdrawals, sensor monitoring, printer jobs, and concurrent operations.

### Module 4 — Java GUI Applications

Form-based applications for attendance, banking, calculator operations, course enrollment, inventory, library management, and student registration.

### Module 5 — Java Web Application

A Maven-based Java Servlet application for submitting and processing feedback.

### Module 6 — Employee Management System

A Java desktop application that uses JDBC and MySQL for employee data management.

## Repository Structure

```text
CSIT751-Core-Java/
├── Module-1/                         # Java fundamentals
├── Module-2/                         # Object-oriented programming
├── Module3/                          # Exceptions and multithreading
├── Module4/                          # GUI applications
├── Module5-main/Module5-main/        # Maven Servlet application
│   ├── src/
│   ├── web/
│   └── pom.xml
├── Module6-main/Module6-main/        # JDBC employee management system
│   ├── src/
│   ├── database_setup.sql
│   └── lib/
└── README.md
```

## Technologies Used

- Java
- Object-oriented programming
- Exception handling and multithreading
- Java Swing
- Java Servlets
- Apache Maven
- JDBC and MySQL

## Getting Started

### Run the standalone programs

1. Install **JDK 17** or later.
2. Open the required module directory.
3. Compile a Java file:

   ```bash
   javac FileName.java
   ```

4. Run the compiled class:

   ```bash
   java ClassName
   ```

### Build Module 5

From `Module5-main/Module5-main/`, run:

```bash
mvn clean package
```

Deploy the generated WAR file to a compatible Java Servlet container such as Apache Tomcat.

### Run Module 6

1. Create the required MySQL database using `database_setup.sql`.
2. Configure the database connection in the application source.
3. Add the MySQL Connector/J library from the module's `lib/` directory.
4. Open and run the project in a Java IDE.

## Purpose

This repository documents the progression from core Java syntax and object-oriented programming to GUI, web, and database-driven applications.
