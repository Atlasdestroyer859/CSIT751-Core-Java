-- Database Setup for Employee Management Application

-- 1. Create database if it does not already exist
CREATE DATABASE IF NOT EXISTS company_db;

-- 2. Switch to company_db
USE company_db;

-- 3. Create employees table as per requirements
CREATE TABLE IF NOT EXISTS employees (
    emp_id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10,2)
);

-- Optional: Sample initial test records
-- INSERT INTO employees (emp_id, name, department, salary) VALUES
-- (101, 'John Doe', 'Engineering', 75000.00),
-- (102, 'Jane Smith', 'Human Resources', 62000.50);
