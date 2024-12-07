package com.jira.service;

import com.jira.model.Project;
import com.jira.repository.ProjectRepository;

import java.sql.SQLException;

public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(Project project) throws SQLException {
        projectRepository.save(project);
        System.out.println("Project added: " + project.getName());
    }

    public void deleteProject(String projectName) throws SQLException {
        projectRepository.deleteByName(projectName);
        System.out.println("Project deleted: " + projectName);
    }
}