package com.example.student.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.student.model.Student;
import com.example.student.util.SceneManager;
import com.example.student.util.StudentData;
import java.util.List;
import java.util.stream.Collectors;

public class SearchStudentController {

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
    private TableColumn<Student, String> departmentColumn;

    @FXML
    private TableColumn<Student, String> batchColumn;

    @FXML
    private Label messageLabel;

    @FXML
    private Label countLabel;

    @FXML
    public void initialize() {
        // Set up table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        batchColumn.setCellValueFactory(new PropertyValueFactory<>("batch"));

        updateCountLabel(0);
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = searchField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            messageLabel.setText("❌ Please enter a search term!");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        List<Student> allStudents = StudentData.getAllStudents();
        List<Student> filteredStudents = allStudents.stream()
                .filter(s -> s.getId().toLowerCase().contains(searchText) ||
                        s.getName().toLowerCase().contains(searchText) ||
                        s.getDepartment().toLowerCase().contains(searchText) ||
                        s.getBatch().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        if (filteredStudents.isEmpty()) {
            messageLabel.setText("❌ No students found matching '" + searchText + "'");
            messageLabel.setStyle("-fx-text-fill: red;");
            studentTable.getItems().clear();
            updateCountLabel(0);
        } else {
            ObservableList<Student> studentList = FXCollections.observableArrayList(filteredStudents);
            studentTable.setItems(studentList);

            messageLabel.setText("✅ Found " + filteredStudents.size() + " student(s)");
            messageLabel.setStyle("-fx-text-fill: green;");
            updateCountLabel(filteredStudents.size());
        }
    }

    @FXML
    private void handleShowAll(ActionEvent event) {
        List<Student> allStudents = StudentData.getAllStudents();

        if (allStudents.isEmpty()) {
            messageLabel.setText("❌ No students in the system!");
            messageLabel.setStyle("-fx-text-fill: red;");
            studentTable.getItems().clear();
            updateCountLabel(0);
        } else {
            ObservableList<Student> studentList = FXCollections.observableArrayList(allStudents);
            studentTable.setItems(studentList);

            messageLabel.setText("✅ Showing all students");
            messageLabel.setStyle("-fx-text-fill: green;");
            updateCountLabel(allStudents.size());
        }

        searchField.clear();
    }

    @FXML
    private void handleClear(ActionEvent event) {
        searchField.clear();
        studentTable.getItems().clear();
        messageLabel.setText("");
        updateCountLabel(0);
    }

    private void updateCountLabel(int count) {
        countLabel.setText("Total Students: " + count);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.switchScene(event, "/views/dashboard.fxml");
    }
}