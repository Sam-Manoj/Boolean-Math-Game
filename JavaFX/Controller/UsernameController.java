package com.example.javaminiproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsernameController {
    @FXML
    private TextField usernameField;
    @FXML
    private Button submitButton;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView backgroundImage;

    private Connection connect;
    String username;

    @FXML
    public void initialize() {
        // Bind ImageView's fitWidth and fitHeight properties to AnchorPane's width and height
        backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
    }

    public String getUsername(){
        return username;
    }

    @FXML
    private void handleSubmitButtonClick() {
        username = usernameField.getText().trim();
        System.out.println("Username submitted: " + username);

        if (username.isEmpty()) {
            System.out.println("Please enter a valid username.");
            return;
        }

        try {
            saveUsernameToDatabase(username);
            loadLevel1WithUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveUsernameToDatabase(String username) throws SQLException {
        String checkUserSql = "SELECT * FROM userinfo WHERE user_id = ?";
        String insertUserSql = "INSERT INTO userinfo (user_id, level_1, level_2) VALUES(?, ?, ?)";

        connect = DatabaseManager.getConnection();

        try (PreparedStatement checkStmt = connect.prepareStatement(checkUserSql)) {
            checkStmt.setString(1, username);
            ResultSet result = checkStmt.executeQuery();

            if (!result.next()) {
                // If the user does not exist, insert them
                try (PreparedStatement insertStmt = connect.prepareStatement(insertUserSql)) {
                    insertStmt.setString(1, username);
                    insertStmt.setInt(2, 0); // Initialize level_1 score
                    insertStmt.setInt(3, 0); // Initialize level_2 score
                    insertStmt.executeUpdate();
                    System.out.println("User added successfully.");
                }
            } else {
                System.out.println("User already exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connect != null) {
                connect.close();
            }
        }
    }

    private void loadLevel1WithUsername(String username) {
        try {
            // Load level1.fxml and set username on the controller
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javaminiproject/level1.fxml"));
            Parent level1Root = loader.load();

            // Get level1 controller and set username
            level1controller level1Controller = loader.getController();
            level1Controller.setUsername(username);

            Scene level1Scene = new Scene(level1Root);
            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.setScene(level1Scene);
            stage.setFullScreen(true); // Set the stage to be full screen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
