package com.jira.ui;

import java.util.Scanner;

public class LoginUI {
    public String[] getCredentials() {
        Scanner scanner = new Scanner(System.in);
        String[] credentials = new String[2];

        System.out.print("Enter Username: ");
        credentials[0] = scanner.nextLine();
        System.out.print("Enter Password: ");
        credentials[1] = scanner.nextLine();

        return credentials;
    }
}