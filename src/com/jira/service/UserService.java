package com.jira.service;

import com.jira.model.User;
import com.jira.repository.UserRepository;

import java.sql.SQLException;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws SQLException {
        System.out.println("Attempting to login with username: " + username);
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            System.out.println("Login successful for user: " + username);
        } else {
            System.out.println("Login failed for user: " + username);
        }
        return user;
    }
    public void addUser(User user) throws SQLException {
        userRepository.save(user);
        System.out.println("User added: " + user.getUsername());
    }

    public void deleteUser(String username) throws SQLException {
        userRepository.deleteByUsername(username);
        System.out.println("User deleted: " + username);
    }

}
