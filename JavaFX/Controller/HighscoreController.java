package com.example.javaminiproject;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label; // Import Label
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HighscoreController {
    @FXML
    private Button helloButton;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private StackPane stackPane;

    // Labels to display high scores
    @FXML
    private Label highScoreUser1;  // Label for the user with the highest score
    @FXML
    private Label highScoreUser2;  // Label for the user with the second highest score
    @FXML
    private Label highScoreUser3;  // Label for the user with the third highest score

    // Initialize method that will be called when FXML is loaded
    @FXML
    public void initialize() {
        // Bind ImageView's size to StackPane's size
        if (stackPane != null && backgroundImageView != null) {
            backgroundImageView.fitWidthProperty().bind(stackPane.widthProperty());
            backgroundImageView.fitHeightProperty().bind(stackPane.heightProperty());
        } else {
            System.err.println("Initialization failed: StackPane or ImageView is null");
        }

        // Load high scores into labels
        loadHighScores();
    }

    private void loadHighScores() {
        // Load the top three users by score for level 1
        highScoreUser1.setText(getUserByScoreRank(1));
        highScoreUser2.setText(getUserByScoreRank(2));
        highScoreUser3.setText(getUserByScoreRank(3));
    }

    private String getUserByScoreRank(int rank) {
        String userId = null;
        String query = "SELECT user_id FROM userinfo ORDER BY level_1 DESC LIMIT 1 OFFSET ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, rank - 1);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString("user_id"); // Fetch user_id instead of username
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId != null ? userId : "No users found"; // Return a default message if no user is found
    }

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
