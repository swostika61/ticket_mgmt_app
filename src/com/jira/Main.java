package com.jira;

import com.jira.config.DatabaseConfig;
import com.jira.model.User;
import com.jira.model.Project;
import com.jira.service.UserService;
import com.jira.service.ProjectService;
import com.jira.repository.UserRepository;
import com.jira.repository.ProjectRepository;
import com.jira.ui.AdminUI;
import com.jira.ui.DeveloperUI;
import com.jira.ui.TesterUI;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            UserRepository userRepository = new UserRepository(connection);
            UserService userService = new UserService(userRepository);

            ProjectRepository projectRepository = new ProjectRepository(connection);
            ProjectService projectService = new ProjectService(projectRepository);

            String username = "admin_user"; // Replace with actual username
            String password = "password123"; // Replace with actual password
            User user = userService.login(username, password);

            if (user != null) {
                showUIBasedOnRole(user, userService, projectService);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add this line to keep the console open
        System.out.println("Press Enter to exit...");
        new Scanner(System.in).nextLine();
    }

    private static void showUIBasedOnRole(User user, UserService userService, ProjectService projectService) {
        if ("admin".equalsIgnoreCase(user.getRole())) {
            AdminUI adminUI = new AdminUI(userService, projectService);
            adminUI.display();
        } else if ("developer".equalsIgnoreCase(user.getRole())) {
            DeveloperUI developerUI = new DeveloperUI();
            developerUI.display();
        } else if ("tester".equalsIgnoreCase(user.getRole())) {
            TesterUI testerUI = new TesterUI();
            testerUI.display();
        } else {
            System.out.println("Unknown role. Showing default UI...");
            // Load Default UI if necessary
        }
    }
}