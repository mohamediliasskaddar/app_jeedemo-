//package com.imk.jeedemo.utils;
//
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.Properties;
//
//public class DBConnection {
//
//    private static Connection connection;
//
//    public static Connection getConnection() throws Exception {
//        if (connection != null && !connection.isClosed()) {
//            System.out.println("connection is already open");
//            return connection;
//        }
//        Properties props = new Properties();
//        InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
//        props.load(input);
//        String url = props.getProperty("db.url");
//        String user = props.getProperty("db.user");
//        String password = props.getProperty("db.password");
//        connection = DriverManager.getConnection(url, user, password);
//        System.out.println("connection established");
//        return connection;
//    }
//}

package com.imk.jeedemo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://jeedemo-db1:5432/jeedemo_db";
    private static final String USER = "imk";
    private static final String PASSWORD = "secret";

    public static Connection getConnection() throws SQLException {
        try {
            // Charger explicitement le driver PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver PostgreSQL introuvable", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
