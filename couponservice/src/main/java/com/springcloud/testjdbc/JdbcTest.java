package com.springcloud.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            String userName = "root";
            String password = "rootroot";

            String url = "jdbc:mysql://localhost:3306/mydb";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Database Connection Terminated");
                } catch (Exception e) {}
            }
        }
    }
}
