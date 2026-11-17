package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeDAO handles all JDBC operations for the employees table.
 */
public class EmployeeDAO {

    // Database configuration
    // Update the port, username, or password according to your local MySQL server setup
    private static final String URL = "jdbc:mysql://localhost:3306/company_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Change to your MySQL password (e.g., "", "root", "admin", "1234")

    /**
     * Inner model class to represent an Employee record.
     */
    public static class Employee {
        private int empId;
        private String name;
        private String department;
        private double salary;

        public Employee(int empId, String name, String department, double salary) {
            this.empId = empId;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public int getEmpId() {
            return empId;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }
    }

    /**
     * Establishes and returns a Connection to the MySQL database.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Try modern MySQL Connector/J driver, fall back to legacy if using older driver jar
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Class.forName("com.mysql.jdbc.Driver");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Inserts a new employee record into the database.
     *
     * @param emp The employee object to insert
     * @return true if insertion succeeded, false otherwise
     */
    public static boolean addEmployee(Employee emp) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employees (emp_id, name, department, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, emp.getEmpId());
            pstmt.setString(2, emp.getName());
            pstmt.setString(3, emp.getDepartment());
            pstmt.setDouble(4, emp.getSalary());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Retrieves all employee records from the database.
     *
     * @return A List of Employee objects
     */
    public static List<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT emp_id, name, department, salary FROM employees ORDER BY emp_id ASC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                double sal = rs.getDouble("salary");

                employeeList.add(new Employee(id, name, dept, sal));
            }
        }
        return employeeList;
    }
}
