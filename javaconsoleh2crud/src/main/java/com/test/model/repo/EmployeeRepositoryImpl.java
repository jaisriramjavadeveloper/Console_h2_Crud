package com.test.model.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	private static final String JDBC_URL = "jdbc:h2:./data/employeeDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public EmployeeRepositoryImpl() {
        // Create table when the repository is initialized
        createTable();
    }

    // Method to create the table if it doesn't exist
    private void createTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS EMPLOYEE (" +
                    "empId INT PRIMARY KEY, " +
                    "fname VARCHAR(255), " +
                    "country VARCHAR(255))";
            stmt.execute(createTableSQL);
            System.out.println("Table EMPLOYEE created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Employee employee) {
        String insertSQL = "INSERT INTO EMPLOYEE (empId, fname, country) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setInt(1, employee.getEmpId());
            pstmt.setString(2, employee.getFname());
            pstmt.setString(3, employee.getCountry());
            pstmt.executeUpdate();
            System.out.println("Employee inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee findById(int empId) {
        String selectSQL = "SELECT * FROM EMPLOYEE WHERE empId = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(selectSQL)) {
            pstmt.setInt(1, empId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("empId"), rs.getString("fname"), rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        String selectSQL = "SELECT * FROM EMPLOYEE";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("empId"), rs.getString("fname"), rs.getString("country")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        String updateSQL = "UPDATE EMPLOYEE SET fname = ?, country = ? WHERE empId = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, employee.getFname());
            pstmt.setString(2, employee.getCountry());
            pstmt.setInt(3, employee.getEmpId());
            pstmt.executeUpdate();
            System.out.println("Employee updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int empId) {
        String deleteSQL = "DELETE FROM EMPLOYEE WHERE empId = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, empId);
            pstmt.executeUpdate();
            System.out.println("Employee deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}