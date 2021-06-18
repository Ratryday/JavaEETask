package com.ratryday.dao;

import com.ratryday.models.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeDB {
    private static String url = "jdbc:mysql://localhost/departmentdb?serverTimezone=Europe/Moscow&useSSL=false";
    private static String username = "root";
    private static String password = "root";

    public static ArrayList<Employee> select(int id) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM departmentdb.employee WHERE departmentID=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int idEmployee = resultSet.getInt(1);
                        String employeeName = resultSet.getString(2);
                        LocalDate hiringDate = resultSet.getDate(3).toLocalDate();
                        int experience = resultSet.getInt(4);
                        String mailingAddress = resultSet.getString(5);
                        int departmentID = resultSet.getInt(6);

                        // Employee Builder
                        Employee employee = new Employee.EmployeeBuilder()
                                .setEmployeeName(employeeName)
                                .setHiringDate(hiringDate)
                                .setExperience(experience)
                                .setMailingAddress(mailingAddress)
                                .setDepartmentID(departmentID)
                                .build();

                        employees.add(employee);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return employees;
    }

    public static Employee selectOne(int id) {
        Employee employee = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM departmentdb.employee WHERE idEmployee=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int idEmployee = resultSet.getInt(1);
                        String employeeName = resultSet.getString(2);
                        LocalDate hiringDate = resultSet.getDate(3).toLocalDate();
                        int experience = resultSet.getInt(4);
                        String mailingAddress = resultSet.getString(5);
                        int departmentID = resultSet.getInt(6);

                        // Employee Builder
                        employee = new Employee.EmployeeBuilder()
                                .setEmployeeName(employeeName)
                                .setHiringDate(hiringDate)
                                .setExperience(experience)
                                .setMailingAddress(mailingAddress)
                                .setDepartmentID(departmentID)
                                .build();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return employee;
    }

    public static Employee selectOne(String mail) {
        Employee employee = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM departmentdb.employee WHERE mailingAddress=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, mail);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        String mailingAddress = resultSet.getString(5);

                        // Employee Builder
                        employee = new Employee.EmployeeBuilder().setMailingAddress(mailingAddress).build();
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

                String sql = "INSERT INTO departmentdb.employee (name, hiringDate, experience, mailingAddress, departmentID) Values (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, employee.getEmployeeName());
                    preparedStatement.setDate(2, java.sql.Date.valueOf(employee.getHiringDate()));
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

                String sql = "UPDATE departmentdb.employee SET name = ?, hiringDate = ?, experience = ?, mailingAddress = ?, departmentID = ? WHERE idEmployee = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, employee.getEmployeeName());
                    preparedStatement.setDate(2, java.sql.Date.valueOf(employee.getHiringDate()));
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

    public static int delete(int idEmployee, int departmentID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "DELETE FROM employee WHERE idEmployee = ? AND departmentID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, idEmployee);
                    preparedStatement.setInt(2, departmentID);

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }
}

