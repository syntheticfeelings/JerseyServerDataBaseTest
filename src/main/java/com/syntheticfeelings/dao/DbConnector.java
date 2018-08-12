package com.syntheticfeelings.dao;

import java.sql.*;

public class DbConnector {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/database_test";
    private static final String USER = "admin";
    private static final String PASS = "admin";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }


    public static void startConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}