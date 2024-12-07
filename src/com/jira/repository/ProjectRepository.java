package com.jira.repository;

import com.jira.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectRepository {
    private Connection connection;

    public ProjectRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(Project project) throws SQLException {
        String query = "INSERT INTO project (name, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.executeUpdate();
        }
    }

    public void deleteByName(String projectName) throws SQLException {
        String query = "DELETE FROM project WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, projectName);
            statement.executeUpdate();
        }
    }
}