package com.ratryday.controller.employee;

import com.ratryday.controller.department.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeDB {
    private static String url = "jdbc:mysql://localhost/departmentdb?serverTimezone=Europe/Moscow&useSSL=false";
    private static String username = "root";
    private static String password = "root";

    public static ArrayList<Employee> select() {

        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
                while (resultSet.next()) {

                    int idEmployee = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    Date hiringDate = resultSet.getDate(3);
                    int experience = resultSet.getInt(4);
                    String mailingAddress = resultSet.getString(5);
                    int departmentID = resultSet.getInt(6);
                    Employee employee = new Employee(idEmployee, name, hiringDate, experience, mailingAddress, departmentID);
                    employees.add(employee);
                }
            }
        } catch (Exception ex) {
            System.out.println( ex);
        }
        return employees;
    }

    public static Employee selectOne(int id) {

        Employee employee = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM employee WHERE id=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {

                        int idEmployee = resultSet.getInt(1);
                        String name = resultSet.getString(2);
                        Date hiringDate = resultSet.getDate(3);
                        int experience = resultSet.getInt(4);
                        String mailingAddress = resultSet.getString(5);
                        int departmentID = resultSet.getInt(6);
                        employee = new Employee(idEmployee, name, hiringDate, experience, mailingAddress, departmentID);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return employee;
    }

    public static int insert(Employee employee) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "INSERT INTO employee (name, hiringDate, experience, mailingAddress, departmentID) Values (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, employee.getName());
                    preparedStatement.setDate(2, (java.sql.Date) employee.getHiringDate());
                    preparedStatement.setInt(3, employee.getExperience());
                    preparedStatement.setString(4, employee.getMailingAddress());
                    preparedStatement.setInt(5, employee.getDepartmentID());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(Employee employee) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "UPDATE employee SET name = ?, hiringDate = ?, experience = ?, mailingAddress = ?, departmentID = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, employee.getName());
                    preparedStatement.setDate(2, (java.sql.Date) employee.getHiringDate());
                    preparedStatement.setInt(3, employee.getExperience());
                    preparedStatement.setString(4, employee.getMailingAddress());
                    preparedStatement.setInt(5, employee.getDepartmentID());
                    preparedStatement.setInt(6, employee.getIdEmployee());

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int delete(int id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "DELETE FROM employee WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }
}

