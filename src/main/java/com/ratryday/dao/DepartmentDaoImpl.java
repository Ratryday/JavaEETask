package com.ratryday.dao;

import com.ratryday.controllers.ConnectionPool;
import com.ratryday.models.Department;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Department department = new Department();

    @Override
    public List<Department> select() {
        List<Department> departments = new ArrayList<Department>();
        Connection connection = connectionPool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM department");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                // Department Builder
                department = new Department.DepartmentBuilder()
                        .setId(id)
                        .setName(name)
                        .build();

                departments.add(department);
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
        return departments;
    }

    @Override
    public Department selectOne(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM department WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return department;
    }

    @Override
    public Department selectOne(String departmentName) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM department WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return department;
    }

    @Override
    public int insert(Department department) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO department (name) Values (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getName());
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

    @Override
    public int update(Department department) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE department SET name = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());
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

    @Override
    public int delete(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM department WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
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
}
