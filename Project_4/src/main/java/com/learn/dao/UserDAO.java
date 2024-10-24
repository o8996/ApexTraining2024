package com.learn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.learn.bean.UserBean;

public class UserDAO {

    // Method to check if the username already exists in the database
    public boolean checkUsernameExists(String username) {
        boolean exists = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sept2024", "root", "admin");
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM user WHERE username = ?")) {

                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    exists = true; // Username is found, so it exists
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return exists;
    }

    // Method to insert a new user into the database
    public boolean insertUser(UserBean userBean) {
        boolean isRegistered = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sept2024", "root", "admin");
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO user (username, password, firstName, lastName) VALUES (?, ?, ?, ?)")) {

                preparedStatement.setString(1, userBean.getUsername());
                preparedStatement.setString(2, userBean.getPassword());
                preparedStatement.setString(3, userBean.getFirstName());
                preparedStatement.setString(4, userBean.getLastName());

                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    isRegistered = true; // Insertion successful
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return isRegistered;
    }

    // Method to authenticate the user during login
    public UserBean getUserBean(UserBean userBean) {
        UserBean authenticatedUser = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sept2024", "root", "admin");
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?")) {

                preparedStatement.setString(1, userBean.getUsername());
                preparedStatement.setString(2, userBean.getPassword());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // User found, populate userBean with data
                    authenticatedUser = new UserBean();
                    authenticatedUser.setUsername(resultSet.getString("username"));
                    authenticatedUser.setFirstName(resultSet.getString("firstName"));
                    authenticatedUser.setLastName(resultSet.getString("lastName"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return authenticatedUser; // Return null if no user is found
    }
}
