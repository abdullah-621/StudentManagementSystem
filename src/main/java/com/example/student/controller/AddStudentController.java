package com.example.student.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import com.example.student.model.Student;
import com.example.student.util.SceneManager;
import com.example.student.util.StudentData;

public class AddStudentController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<String> departmentCombo;

    @FXML
    private TextField batchField;

    @FXML
    private TextField semesterField;

    @FXML
    private TextField cgpaField;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        // Populate department dropdown
        departmentCombo.getItems().addAll(
            "CSE - Computer Science and Engineering",
            "EEE - Electrical and Electronic Engineering",
            "CE - Civil Engineering",
            "ME - Mechanical Engineering",
            "BBA - Business Administration",
            "English",
            "LLB - Law"
        );
    }

    @FXML
    private void handleAddStudent(ActionEvent event) {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String department = departmentCombo.getValue();
        String batch = batchField.getText().trim();
        String semester = semesterField.getText().trim();
        String cgpa = cgpaField.getText().trim();

        // Validation
        if (id.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || 
            department == null || batch.isEmpty() || semester.isEmpty() || cgpa.isEmpty()) {
            messageLabel.setText("❌ Please fill in all fields!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Check if ID already exists
        if (StudentData.findStudentById(id) != null) {
            messageLabel.setText("❌ Student ID already exists!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Email validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            messageLabel.setText("❌ Invalid email format!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Phone validation (basic)
        if (!phone.matches("^[0-9]{11,15}$")) {
            messageLabel.setText("❌ Phone must be 11-15 digits!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Batch validation (should be a number)
        try {
            int batchNum = Integer.parseInt(batch);
            if (batchNum < 1 || batchNum > 100) {
                messageLabel.setText("❌ Batch must be between 1 and 100!");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("❌ Batch must be a valid number!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // CGPA validation
        try {
            double cgpaValue = Double.parseDouble(cgpa);
            if (cgpaValue < 0.0 || cgpaValue > 4.0) {
                messageLabel.setText("❌ CGPA must be between 0.0 and 4.0!");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("❌ CGPA must be a valid number!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Create and add student
        Student student = new Student(id, name, email, phone, department, batch, semester, cgpa);
        StudentData.addStudent(student);

        messageLabel.setText("✅ Student added successfully!");
        messageLabel.setStyle("-fx-text-fill: green;");

        // Clear fields after 1.5 seconds
        javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));
        pause.setOnFinished(e -> handleClear(null));
        pause.play();
    }

    @FXML
    private void handleClear(ActionEvent event) {
        idField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        departmentCombo.setValue(null);
        batchField.clear();
        semesterField.clear();
        cgpaField.clear();
        messageLabel.setText("");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "/views/dashboard.fxml");
    }
}