package com.jira.repository;

import com.jira.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        String query = "SELECT * FROM user WHERE u_name = ? AND u_password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("u_id");
                String role = resultSet.getString("u_role");
                return new User(id, username, password, role);
            }
        }
        return null;
    }

    public void save(User user) throws SQLException {
        String query = "INSERT INTO user (u_name, u_password, u_role) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        }
    }

    public void deleteByUsername(String username) throws SQLException {
        String query = "DELETE FROM user WHERE u_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
    }
}