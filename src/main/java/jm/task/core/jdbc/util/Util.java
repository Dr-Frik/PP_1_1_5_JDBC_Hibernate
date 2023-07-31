package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/schema_test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2833";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME , PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("CONNECTION ERROR");
        }
        return connection;
    }
}
