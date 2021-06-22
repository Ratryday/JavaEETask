package com.ratryday.controllers;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionPool {

    private ConnectionPool() {
    }

    private static ConnectionPool instance = null;
    private DataSource dataSource;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/companyPool");
            connection = dataSource.getConnection();
        } catch (NamingException namingException) {
            namingException.printStackTrace();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}
