package com.example.javaminiproject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class GameMenuController {
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private StackPane stackPane;
    // Initialize method that will be called when FXML is loaded
    @FXML
    public void initialize() {
        // Check if stackPane and backgroundImageView are not null
        if (stackPane != null && backgroundImageView != null) {
            // Bind ImageView's size to StackPane's size, so it adjusts to the window size
            backgroundImageView.fitWidthProperty().bind(stackPane.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stackPane.heightProperty());
        } else {
            System.err.println("Initialization failed: StackPane or ImageView is null");
        }
    }
    // Method to change the scene
    private void changeScene(ActionEvent event, String fxmlFile) {
        try {
            // Load the specified FXML file
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            // Create a new scene
            Scene scene = new Scene(root);
            // Get the current stage from the clicked button
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene
            stage.setScene(scene);
            stage.setFullScreen(true); // Optional: maximize the window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void aboutclick(ActionEvent event) {
        changeScene(event, "/com/example/javaminiproject/AboutGame.fxml");
        System.out.println("opened About us");
    }
    @FXML
    private void playclick(ActionEvent event) {
        changeScene(event, "/com/example/javaminiproject/StartGame.fxml");
        System.out.println("opened game");
    }
    @FXML
    private void setclick(ActionEvent event) {
        changeScene(event, "/com/example/javaminiproject/settings.fxml");
        System.out.println("opened settings");
    }
    @FXML
    private void hiclick(ActionEvent event) {
        changeScene(event, "/com/example/javaminiproject/Highscore.fxml");
        System.out.println("opened highschore");
    }
}
