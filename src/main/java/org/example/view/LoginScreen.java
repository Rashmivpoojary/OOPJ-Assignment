package org.example.view;

import org.example.controller.UserController;

import org.example.database.UserDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 25);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 25);
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 25);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                UserDAO userDAO = new UserDAO();

                // Check if user is an admin
                if (userDAO.isAdmin(username)) {
                    dispose();
                    new AdminPanel(); // Redirect to AdminPanel
                } else {
                    // Authenticate user
                    int userId = userDAO.authenticateUser(username, password);
                    if (userId != -1) {
                        dispose();
                        new UserPanel(userId); // Redirect to UserPanel with userId
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.");
                    }
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginScreen();
}
}