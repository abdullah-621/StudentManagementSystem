package com.example.student.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import com.example.student.model.Student;
import com.example.student.util.SceneManager;
import com.example.student.util.StudentData;

import java.util.List;

public class DeleteStudentController {

    @FXML
    private TextField searchIdField;

    @FXML
    private VBox studentDetailsBox;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label semesterLabel;

    @FXML
    private Label cgpaLabel;

    @FXML
    private Button deleteButton;

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
            studentDetailsBox.setVisible(false);
            deleteButton.setDisable(true);
            return;
        }

        // Display student details
        idLabel.setText(currentStudent.getId());
        nameLabel.setText(currentStudent.getName());
        emailLabel.setText(currentStudent.getEmail());
        phoneLabel.setText(currentStudent.getPhone());
        semesterLabel.setText(currentStudent.getSemester());
        cgpaLabel.setText(currentStudent.getCgpa());

        studentDetailsBox.setVisible(true);
        deleteButton.setDisable(false);
        messageLabel.setText("✅ Student found! Review details before deleting.");
        messageLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        if (currentStudent == null) {
            messageLabel.setText("⚠ Please search for a student first!");
            messageLabel.setStyle("-fx-text-fill: orange;");
            return;
        }

        if (StudentData.deleteStudent(currentStudent.getId())) {
            messageLabel.setText("✅ Student deleted successfully!");
            messageLabel.setStyle("-fx-text-fill: green;");

            // Clear after delay
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(
                    javafx.util.Duration.seconds(1.5)
            );
            pause.setOnFinished(e -> handleClear(null));
            pause.play();
        } else {
            messageLabel.setText("❌ Failed to delete student!");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        searchIdField.clear();
        studentDetailsBox.setVisible(false);
        deleteButton.setDisable(true);
        messageLabel.setText("");
        currentStudent = null;
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "/views/dashboard.fxml");
    }
}