package com.example.student.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.example.student.util.SceneManager;
import com.example.student.util.StudentData;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label studentCountLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateStudentCount();
    }

    private void updateStudentCount() {
        int count = StudentData.getAllStudents().size();
        studentCountLabel.setText("Total Students: " + count);
    }

    @FXML
    private void handleAddStudent(ActionEvent event) {
        SceneManager.switchScene(event, "/views/add-student.fxml");
    }

    @FXML
    private void handleUpdateStudent(ActionEvent event) {
        SceneManager.switchScene(event, "/views/update-student.fxml");
    }

    @FXML
    private void handleDeleteStudent(ActionEvent event) {
        SceneManager.switchScene(event, "/views/delete-student.fxml");
    }

    @FXML
    private void handleSearchStudent(ActionEvent event) {
        SceneManager.switchScene(event, "/views/search-student.fxml");
    }

    @FXML
    private void handleAllStudents(ActionEvent event) {
        SceneManager.switchScene(event, "/views/all-students.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneManager.switchScene(event, "/views/login.fxml");
    }
}