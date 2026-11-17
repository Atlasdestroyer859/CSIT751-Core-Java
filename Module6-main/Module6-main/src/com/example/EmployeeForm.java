package com.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * EmployeeForm is the Swing GUI frontend for the Employee Management desktop application.
 */
public class EmployeeForm extends JFrame {

    // Input fields for adding a new employee
    private JTextField txtEmpId;
    private JTextField txtName;
    private JTextField txtDepartment;
    private JTextField txtSalary;

    // Action buttons
    private JButton btnSubmit;
    private JButton btnViewAll;

    // Table and TableModel for displaying employee records
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeeForm() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(650, 500));
        setLocationRelativeTo(null); // Center on screen

        initUI();
    }

    private void initUI() {
        // Main panel with BorderLayout and padding
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        // Header Title
        JLabel lblHeader = new JLabel("Employee Management System", JLabel.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblHeader.setForeground(new Color(30, 41, 59));
        mainPanel.add(lblHeader, BorderLayout.NORTH);

        // Center container holding Form Panel (Top) and Table Panel (Bottom)
        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));

        // 1. FORM PANEL (GridBagLayout for neat alignment)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), " Add New Employee ",
                0, 0, new Font("Segoe UI", Font.BOLD, 14), new Color(51, 65, 85)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0: Employee ID
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.15;
        formPanel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.85;
        txtEmpId = new JTextField(15);
        formPanel.add(txtEmpId, gbc);

        // Row 1: Name
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.15;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.85;
        txtName = new JTextField(15);
        formPanel.add(txtName, gbc);

        // Row 2: Department
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.15;
        formPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0.85;
        txtDepartment = new JTextField(15);
        formPanel.add(txtDepartment, gbc);

        // Row 3: Salary
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.15;
        formPanel.add(new JLabel("Salary ($):"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.85;
        txtSalary = new JTextField(15);
        formPanel.add(txtSalary, gbc);

        // Row 4: Buttons (Submit and View All)
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.weightx = 1.0;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSubmit.setFocusPainted(false);
        btnSubmit.setBackground(new Color(37, 99, 235));
        btnSubmit.setForeground(Color.WHITE);

        btnViewAll = new JButton("View All Employees");
        btnViewAll.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnViewAll.setFocusPainted(false);
        btnViewAll.setBackground(new Color(16, 185, 129));
        btnViewAll.setForeground(Color.WHITE);

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnViewAll);
        formPanel.add(buttonPanel, gbc);

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // 2. TABLE PANEL
        String[] columnNames = {"Employee ID", "Name", "Department", "Salary ($)"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells read-only
            }
        };

        employeeTable = new JTable(tableModel);
        employeeTable.setRowHeight(25);
        employeeTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        employeeTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        employeeTable.setFillsViewportHeight(true);

        // Center align the Employee ID column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        employeeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), " Employee Records ",
                0, 0, new Font("Segoe UI", Font.BOLD, 14), new Color(51, 65, 85)));

        centerPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Register Action Listeners
        registerListeners();
    }

    private void registerListeners() {
        // Submit button click handler
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        // View All Employees button click handler
        btnViewAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllEmployees();
            }
        });
    }

    /**
     * Validates input fields and saves the employee into the database.
     */
    private void handleSubmit() {
        String empIdStr = txtEmpId.getText().trim();
        String name = txtName.getText().trim();
        String department = txtDepartment.getText().trim();
        String salaryStr = txtSalary.getText().trim();

        // Check for empty fields
        if (empIdStr.isEmpty() || name.isEmpty() || department.isEmpty() || salaryStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields (ID, Name, Department, and Salary).",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate integer ID
        int empId;
        try {
            empId = Integer.parseInt(empIdStr);
            if (empId <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Employee ID must be a positive integer.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Employee ID must be a valid integer number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate decimal Salary
        double salary;
        try {
            salary = Double.parseDouble(salaryStr);
            if (salary < 0) {
                JOptionPane.showMessageDialog(this,
                        "Salary cannot be negative.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Salary must be a valid decimal number (e.g., 55000.00).",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save via DAO
        try {
            EmployeeDAO.Employee emp = new EmployeeDAO.Employee(empId, name, department, salary);
            boolean success = EmployeeDAO.addEmployee(emp);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Employee added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear input fields
                clearForm();

                // Refresh table to display new record immediately
                loadAllEmployees();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to add employee. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) { // Duplicate primary key error in MySQL
                JOptionPane.showMessageDialog(this,
                        "An employee with ID " + empId + " already exists in the database.",
                        "Duplicate Employee ID",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Database Error: " + ex.getMessage(),
                        "SQL Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    "MySQL JDBC Driver not found!\nMake sure mysql-connector-java.jar is added to the classpath.",
                    "Driver Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Retrieves all employee records from the database and populates the JTable.
     */
    private void loadAllEmployees() {
        // Clear existing rows in table
        tableModel.setRowCount(0);

        try {
            List<EmployeeDAO.Employee> employeeList = EmployeeDAO.getAllEmployees();

            if (employeeList.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No employee records found in the database.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (EmployeeDAO.Employee emp : employeeList) {
                Object[] rowData = {
                        emp.getEmpId(),
                        emp.getName(),
                        emp.getDepartment(),
                        String.format("%.2f", emp.getSalary())
                };
                tableModel.addRow(rowData);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Database Error while fetching records:\n" + ex.getMessage(),
                    "SQL Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this,
                    "MySQL JDBC Driver not found!\nMake sure mysql-connector-java.jar is added to the classpath.",
                    "Driver Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Clears input fields after submission.
     */
    private void clearForm() {
        txtEmpId.setText("");
        txtName.setText("");
        txtDepartment.setText("");
        txtSalary.setText("");
        txtEmpId.requestFocus();
    }

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        // Set modern look and feel if available
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // Launch GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeForm().setVisible(true);
            }
        });
    }
}
