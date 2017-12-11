package com.school.dao;

import java.sql.*;


public abstract class DBConnection {

    private final String tableName;
    protected static Connection conn;

    public DBConnection(String tableName) {

        this.tableName = tableName;
        conn = connectToDataBase(this.tableName);
    }

    public static Connection connectToDataBase(String dataBasePath) {

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/school/sql/school.db");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

