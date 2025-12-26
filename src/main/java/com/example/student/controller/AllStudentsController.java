package com.example.student.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.example.student.model.Student;
import com.example.student.util.SceneManager;
import com.example.student.util.StudentData;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AllStudentsController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    private TableColumn<Student, String> phoneColumn;

    @FXML
    private TableColumn<Student, String> semesterColumn;

    @FXML
    private TableColumn<Student, String> cgpaColumn;

    @FXML
    private Label messageLabel;

    @FXML
    private Label countLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllStudents();
    }

    private void loadAllStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList(
                StudentData.getAllStudents()
        );
        studentTable.setItems(students);
        updateCount(students.size());
        messageLabel.setText("");
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            messageLabel.setText("⚠ Please enter search text!");
            messageLabel.setStyle("-fx-text-fill: orange;");
            return;
        }

        List<Student> allStudents = StudentData.getAllStudents();
        List<Student> filteredStudents = new ArrayList<>();

        for (Student student : allStudents) {
            if (student.getId().toLowerCase().contains(searchText) ||
                student.getName().toLowerCase().contains(searchText) ||
                student.getEmail().toLowerCase().contains(searchText)) {
                filteredStudents.add(student);
            }
        }

        ObservableList<Student> observableList = FXCollections.observableArrayList(filteredStudents);
        studentTable.setItems(observableList);
        updateCount(filteredStudents.size());

        if (filteredStudents.isEmpty()) {
            messageLabel.setText("❌ No students found matching: " + searchText);
            messageLabel.setStyle("-fx-text-fill: red;");
        } else {
            messageLabel.setText("✅ Found " + filteredStudents.size() + " student(s)");
            messageLabel.setStyle("-fx-text-fill: green;");
        }
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadAllStudents();
        searchField.clear();
        messageLabel.setText("✅ List refreshed!");
        messageLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    private void handleClear(ActionEvent event) {
        searchField.clear();
        loadAllStudents();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "/views/dashboard.fxml");
    }

    private void updateCount(int count) {
        countLabel.setText("Total Students: " + count);
    }
}