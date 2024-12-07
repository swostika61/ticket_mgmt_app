package com.jira.ui;

import com.jira.model.User;
import com.jira.model.Project;
import com.jira.service.UserService;
import com.jira.service.ProjectService;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminUI {
    private UserService userService;
    private ProjectService projectService;

    public AdminUI(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Admin UI: Manage users, projects, etc.");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Add Project");
            System.out.println("4. Delete Project");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role:('admin', 'tester', 'developer' )");
                    String role = scanner.nextLine();
                    try {
                        userService.addUser(new User(0, username, password, role));  // ID is auto-generated
                        System.out.println("User added successfully.");
                    } catch (SQLException e) {
                        System.out.println("Error adding user: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter username to delete: ");
                    String usernameToDelete = scanner.nextLine();
                    try {
                        userService.deleteUser(usernameToDelete);
                        System.out.println("User deleted successfully.");
                    } catch (SQLException e) {
                        System.out.println("Error deleting user: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter project name: ");
                    String projectName = scanner.nextLine();
                    System.out.print("Enter project description: ");
                    String projectDescription = scanner.nextLine();
                    try {
                        projectService.addProject(new Project(projectName, projectDescription));
                        System.out.println("Project added successfully.");
                    } catch (SQLException e) {
                        System.out.println("Error adding project: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter project name to delete: ");
                    String projectNameToDelete = scanner.nextLine();
                    try {
                        projectService.deleteProject(projectNameToDelete);
                        System.out.println("Project deleted successfully.");
                    } catch (SQLException e) {
                        System.out.println("Error deleting project: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Exiting Admin UI.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}