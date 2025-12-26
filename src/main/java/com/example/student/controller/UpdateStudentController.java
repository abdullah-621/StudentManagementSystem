package com.example.student.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.student.model.Student;
import com.example.student.util.SceneManager;
import com.example.student.util.StudentData;

import java.util.List;

public class UpdateStudentController {

    @FXML
    private TextField searchIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField semesterField;

    @FXML
    private TextField cgpaField;

    @FXML
    private Button updateButton;

    @FXML
    private Label messageLabel;

    private Student currentStudent;

    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchIdField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            messageLabel.setText("⚠ Please enter Student ID, Name, or Email!");
            messageLabel.setStyle("-fx-text-fill: orange;");
            return;
        }

        // Search by ID, Name, or Email
        currentStudent = null;
        List<Student> allStudents = StudentData.getAllStudents();

        for (Student student : allStudents) {
            if (student.getId().toLowerCase().equals(searchText) ||
                student.getName().toLowerCase().contains(searchText) ||
                student.getEmail().toLowerCase().contains(searchText)) {
                currentStudent = student;
                break;
            }
        }

        if (currentStudent == null) {
            messageLabel.setText("❌ Student not found!");
            messageLabel.setStyle("-fx-text-fill: red;");
            clearFields();
            disableFields();
            return;
        }

        // Populate fields with current student data
        nameField.setText(currentStudent.getName());
        emailField.setText(currentStudent.getEmail());
        phoneField.setText(currentStudent.getPhone());
        semesterField.setText(currentStudent.getSemester());
        cgpaField.setText(currentStudent.getCgpa());

        enableFields();
        messageLabel.setText("✅ Student found! You can now update the details.");
        messageLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        if (currentStudent == null) {
            messageLabel.setText("⚠ Please search for a student first!");
            messageLabel.setStyle("-fx-text-fill: orange;");
            return;
        }

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String semester = semesterField.getText().trim();
        String cgpa = cgpaField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || 
            semester.isEmpty() || cgpa.isEmpty()) {
            messageLabel.setText("❌ Please fill in all fields!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Email validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            messageLabel.setText("❌ Invalid email format!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Phone validation
        if (!phone.matches("^[0-9]{10,15}$")) {
            messageLabel.setText("❌ Phone must be 10-15 digits!");
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

        // Update student
        Student updatedStudent = new Student(
                currentStudent.getId(), name, email, phone, semester, cgpa
        );

        if (StudentData.updateStudent(currentStudent.getId(), updatedStudent)) {
            messageLabel.setText("✅ Student updated successfully!");
            messageLabel.setStyle("-fx-text-fill: green;");

            // Clear after delay
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1.5)
            );
            pause.setOnFinished(e -> handleClear(null));
            pause.play();
        } else {
            messageLabel.setText("❌ Failed to update student!");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        searchIdField.clear();
        clearFields();
        disableFields();
        messageLabel.setText("");
        currentStudent = null;
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "/views/dashboard.fxml");
    }

    private void clearFields() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        semesterField.clear();
        cgpaField.clear();
    }

    private void enableFields() {
        nameField.setDisable(false);
        emailField.setDisable(false);
        phoneField.setDisable(false);
        semesterField.setDisable(false);
        cgpaField.setDisable(false);
        updateButton.setDisable(false);
    }

    private void disableFields() {
        nameField.setDisable(true);
        emailField.setDisable(true);
        phoneField.setDisable(true);
        semesterField.setDisable(true);
        cgpaField.setDisable(true);
        updateButton.setDisable(true);
    }
}