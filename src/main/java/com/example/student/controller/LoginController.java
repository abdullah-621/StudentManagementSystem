package com.example.student.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TextField passwordVisibleField;

    @FXML
    private Button togglePasswordButton;

    @FXML
    private Label errorLabel;

    private boolean passwordVisible = false;

    @FXML
    public void initialize() {
        // Initially hide the visible password field
        passwordVisibleField.setVisible(false);
        passwordVisibleField.setManaged(false);
        
        // Sync password fields
        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            passwordVisibleField.setText(newVal);
        });
        
        passwordVisibleField.textProperty().addListener((obs, oldVal, newVal) -> {
            passwordField.setText(newVal);
        });
    }

    @FXML
    private void togglePasswordVisibility(ActionEvent event) {
        passwordVisible = !passwordVisible;
        
        if (passwordVisible) {
            // Show password
            passwordVisibleField.setVisible(true);
            passwordVisibleField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
            togglePasswordButton.setText("👁");
        } else {
            // Hide password
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            togglePasswordButton.setText("👁‍🗹");
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("❌ Please enter both username and password!");
            errorLabel.setStyle("-fx-text-fill: red;");
            return;
        }

    
        if (username.equals("alpineIbex") && password.equals("javafx65")) {
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
        passwordVisibleField.clear();
        errorLabel.setText("");
    }
}