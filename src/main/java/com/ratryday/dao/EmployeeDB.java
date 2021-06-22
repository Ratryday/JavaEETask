package com.ratryday.dao;

import com.ratryday.controllers.ConnectionPool;
import com.ratryday.models.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class EmployeeDB {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Employee employee = new Employee();


    public ArrayList<Employee> select(int id) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM departmentdb.employee WHERE departmentID=?";
        EmployeeDB employeeDB = new EmployeeDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employees.add(employeeDB.getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return employees;
    }

    public Employee selectOne(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM departmentdb.employee WHERE idEmployee=?";
        EmployeeDB employeeDB = new EmployeeDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = employeeDB.getEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return employee;
    }

    public Employee selectOne(String mail) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM departmentdb.employee WHERE mailingAddress=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String mailingAddress = resultSet.getString(5);
                // Employee Builder
                employee = new Employee.EmployeeBuilder().setMailingAddress(mailingAddress).build();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return employee;
    }

    public int insert(Employee employee) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO departmentdb.employee (name, hiringDate, experience, mailingAddress, departmentID) Values (?, ?, ?, ?, ?)";
        EmployeeDB employeeDB = new EmployeeDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return employeeDB.getPreparedStatement(preparedStatement, connection, sql, employee).executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return 0;
    }

    public int update(Employee employee) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE departmentdb.employee SET name = ?, hiringDate = ?, experience = ?, mailingAddress = ?, departmentID = ? WHERE idEmployee = ?";
        EmployeeDB employeeDB = new EmployeeDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement = employeeDB.getPreparedStatement(preparedStatement, connection, sql, employee);
            preparedStatement.setInt(6, employee.getIdEmployee());
            return preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return 0;
    }

    public int delete(int idEmployee, int departmentID) {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM employee WHERE idEmployee = ? AND departmentID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idEmployee);
            preparedStatement.setInt(2, departmentID);
            return preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {

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

        return employee;
    }

    private PreparedStatement getPreparedStatement(PreparedStatement preparedStatement, Connection connection, String sql, Employee employee)
            throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getEmployeeName());
        preparedStatement.setDate(2, java.sql.Date.valueOf(employee.getHiringDate()));
        preparedStatement.setInt(3, employee.getExperience());
        preparedStatement.setString(4, employee.getMailingAddress());
        preparedStatement.setInt(5, employee.getDepartmentID());
        return preparedStatement;
    }
}

