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

            // Create scene with default size
            Scene scene = new Scene(loader.load(), 1150, 690);

            primaryStage.setTitle("Student Management System");
            primaryStage.setScene(scene);
            
            // Set minimum size to prevent UI breaking
            primaryStage.setMinWidth(1150);
            primaryStage.setMinHeight(690);
            
            // Allow resizing
            primaryStage.setResizable(true);
            
            // Center window on screen
            primaryStage.centerOnScreen();
            
            // Show the stage
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