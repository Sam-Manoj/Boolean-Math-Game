package com.example.javaminiproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button; // Import Button instead of MediaView
import java.io.IOException;
import java.util.Objects;

public class SettingsController {
    @FXML
    private Button helloButton;
    @FXML
    protected void BackClick() {
        try {
            Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javaminiproject/GameMenu.fxml")));
            Scene menuScene = new Scene(menuRoot);
            Stage stage = (Stage) helloButton.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setFullScreen(true); // Set the stage to be maximized
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
