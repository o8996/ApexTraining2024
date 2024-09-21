package com.example.mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/sept2024";
        String user = "root";
        String password = "admin"; 
        
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            conn = DriverManager.getConnection(url, user, password);
            
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO person (personID, lastname, firstname, City) VALUES (2, 'Sam', 'Harris', 'Los Angeles')";
            stmt.executeUpdate(query);
            
            System.out.println("Connection successful!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
