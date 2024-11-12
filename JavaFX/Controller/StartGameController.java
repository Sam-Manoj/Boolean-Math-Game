package com.example.javaminiproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Objects;


public class StartGameController {
    @FXML
    private Button button1;  // Easy button
    @FXML
    private Button button2;  // Medium button
    @FXML
    private Button button3;  // Hard button
    @FXML
    private ImageView backgroundImage;
    @FXML
    private AnchorPane anchorPane;

    private String username; // Add a username field


    @FXML
    public void initialize() {
        backgroundImage.fitWidthProperty().bind(anchorPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(anchorPane.heightProperty());
        updateButtonStates(); // Call the method to set button states at initialization

    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void updateButtonStates() {
        int level1Score = DatabaseManager.getScore(1); // Retrieve score for level 1
        int level2Score = DatabaseManager.getScore(2); // Retrieve score for level 2

        //button1.setDisable(level1Score > 0); // Disable easy button if level 1 is completed
        //button2.setDisable(level1Score == 0 || level2Score > 0); // Enable medium button if level 1 is completed
        //button3.setDisable(level2Score == 0); // Enable hard button if level 2 is completed
    }

    @FXML
    protected void BackClick() {
        try {
            Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javaminiproject/GameMenu.fxml")));
            Scene menuScene = new Scene(menuRoot);
            Stage stage = (Stage) button1.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleEasyButtonClick(ActionEvent event) {
        loadScene("/com/example/javaminiproject/username.fxml");
    }

    @FXML
    protected void handleMediumButtonClick(ActionEvent event) {
        navigateToLevel("/com/example/javaminiproject/level2.fxml");
    }

    @FXML
    protected void handleHardButtonClick(ActionEvent event) {
        loadScene("/com/example/javaminiproject/level3.fxml");
    }

    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) button2.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void navigateToLevel(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Set the username for level2 if loading level2.fxml
            if (fxmlPath.contains("level2.fxml")) {
                level2controller level2Controller = loader.getController();
                level2Controller.setUsername(username); // Pass the username to level2controller
            }

            Stage stage = (Stage) button1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
