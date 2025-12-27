package com.example.student.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/views/login.fxml")
            );

            Scene scene = new Scene(loader.load(), 1150, 690);

            primaryStage.setTitle("Student Management System");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Force window dimensions
            primaryStage.setWidth(1150);
            primaryStage.setHeight(718);

            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Error loading application!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}