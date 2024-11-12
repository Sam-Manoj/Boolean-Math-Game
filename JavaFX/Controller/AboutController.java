package com.example.javaminiproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button; // Import Button instead of MediaView
import java.io.IOException;
import java.util.Objects;
public class AboutController {

    @FXML
    private Button helloButton;
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
        }}

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
