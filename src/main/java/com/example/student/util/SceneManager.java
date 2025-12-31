package com.example.student.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    public static void switchScene(ActionEvent event, String fxmlPath) {
        try {
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();
            
            // Remember current state
            boolean wasMaximized = stage.isMaximized();
            boolean wasFullScreen = stage.isFullScreen();
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            double currentX = stage.getX();
            double currentY = stage.getY();
            
            // Load new scene
            Parent root = FXMLLoader.load(
                    SceneManager.class.getResource(fxmlPath)
            );
            
            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            
            // Restore the state
            if (wasMaximized) {
                stage.setMaximized(true);
            } else if (wasFullScreen) {
                stage.setFullScreen(true);
            } else {
                // Restore previous size and position
                stage.setWidth(currentWidth);
                stage.setHeight(currentHeight);
                stage.setX(currentX);
                stage.setY(currentY);
            }
            
            stage.show();

        } catch (Exception e) {
            System.err.println("Scene switch failed: " + fxmlPath);
            e.printStackTrace();
        }
    }
}