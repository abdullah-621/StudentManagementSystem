package com.example.student.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.student.util.SceneManager;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("❌ Please enter both username and password!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Simple authentication (username: admin, password: admin)
        if (username.equals("admin") && password.equals("admin")) {
            errorLabel.setText("");
            SceneManager.switchScene(event, "/views/dashboard.fxml");
        } else {
            errorLabel.setText("❌ Invalid username or password!");
            errorLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        usernameField.clear();
        passwordField.clear();
        errorLabel.setText("");
    }
}