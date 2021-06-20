package com.ratryday.dao;

import com.ratryday.models.Department;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentDB {
    private static String url = "jdbc:mysql://localhost/departmentdb?serverTimezone=Europe/Moscow&useSSL=false";
    private static String username = "root";
    private static String password = "root";

    public ArrayList<Department> select() {

        ArrayList<Department> departments = new ArrayList<Department>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM department");
                while (resultSet.next()) {

                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);

                    // Department Builder
                    Department department = new Department.DepartmentBuilder()
                            .setId(id)
                            .setName(name)
                            .build();

                    departments.add(department);
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return departments;
    }

    public Department selectOne(int id) {
        Department department = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT * FROM department WHERE id=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int departmentId = resultSet.getInt(1);
                        String name = resultSet.getString(2);

                        // Department Builder
                        department = new Department.DepartmentBuilder()
                                .setId(departmentId)
                                .setName(name)
                                .build();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return department;
    }

    public Department selectOne(String departmentName) {
        Department department = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT * FROM department WHERE name=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, departmentName);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int departmentId = resultSet.getInt(1);
                        String name = resultSet.getString(2);

                        // Department Builder
                        department = new Department.DepartmentBuilder()
                                .setId(departmentId)
                                .setName(name)
                                .build();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return department;
    }

    public int insert(Department department) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "INSERT INTO department (name) Values (?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, department.getName());

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int update(Department department) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "UPDATE department SET name = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, department.getName());
                    preparedStatement.setInt(2, department.getId());

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int delete(int id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                String sql = "DELETE FROM department WHERE id = ?";
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
