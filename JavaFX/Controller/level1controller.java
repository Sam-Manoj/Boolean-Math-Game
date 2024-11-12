package com.example.javaminiproject;
//classes needed for background image and button
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//classes needed for database connection
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
//classes needed for timer
import java.util.Timer;
import java.util.TimerTask;
public class level1controller {
    public Button submitButton;
    @FXML
    private Label questionLabel;
    @FXML
    private TextField answerField;
    @FXML
    private Button helloButton;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private StackPane stackPane;
    private int score = 0;
    private int currentQuestionIndex = 0;
    private final int number_of_questions = 5;
    private final String[] questions = new String[number_of_questions];
    private final int[] correctAnswers = new int[number_of_questions];
    private Timer timer;
    private int timeRemaining = 210; // 1 minute for level 1
    private String username; // To store the username for database update
    // Database connection variables
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    public void initialize() {
        // Bind the ImageView dimensions to the StackPane dimensions
        backgroundImageView.fitWidthProperty().bind(stackPane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stackPane.heightProperty());
        generateQuestions();
        updateQuestion();
        startTimer();
    }
    private void generateQuestions() {
        Random rn = new Random();
        for (int i = 0; i < number_of_questions; i++) {
            int a = rn.nextInt(10);
            int b = rn.nextInt(10);
            int choice = rn.nextInt(4);
            switch (choice) {
                case 0 -> {
                    questions[i] = "What is: " + a + " OR " + b + "?";
                    correctAnswers[i] = a | b; // Bitwise OR
                }
                case 1 -> {
                    questions[i] = "What is: " + a + " AND " + b + "?";
                    correctAnswers[i] = a & b; // Bitwise AND
                }
                case 2 -> {
                    questions[i] = "What is: ~" + a + "?";
                    correctAnswers[i] = ~a & 1; // Bitwise NOT, masking to get last bit
                }
                case 3 -> {
                    questions[i] = "What is: " + a + " EX-OR " + b + "?";
                    correctAnswers[i] = a ^ b; // Bitwise XOR
                }
            }
        }
    }
    private void updateQuestion() {
        if (currentQuestionIndex < number_of_questions) {
            questionLabel.setText(questions[currentQuestionIndex]);
            answerField.clear();
        } else {
            endGame();
        }
    }
    @FXML
    private void handleSubmitButtonClick() {
        int userAnswer;
        try {
            userAnswer = Integer.parseInt(answerField.getText());
        } catch (NumberFormatException e) {
            questionLabel.setText("Please enter a valid number.");
            return;
        }
        if (userAnswer == correctAnswers[currentQuestionIndex]) {
            score++;
            scoreLabel.setText(String.valueOf(score));
            updateScoreInDatabase(); // Call to update score in the database
        }
        currentQuestionIndex++;
        updateQuestion();
    }
    private void startTimer() {
        timeLabel.setText(String.valueOf(timeRemaining));
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    timeRemaining--;
                    Platform.runLater(() -> timeLabel.setText(String.valueOf(timeRemaining)));
                    helloButton.setDisable(true);
                    submitButton.setDisable(false);
                } else {
                    endGame();
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }
    private void endGame() {
        questionLabel.setText("Game Over! Your final score is: " + score);
        answerField.setDisable(true);

        if (timer != null) {
            timer.cancel();
            helloButton.setDisable(false);
            submitButton.setDisable(true);
        }
    }
    private void updateScoreInDatabase() {
        // Ensure username is not null before updating
        if (username == null || username.isEmpty()) {
            System.out.println("Username is not set. Cannot update score.");
            return;
        }
        String sql = "UPDATE userinfo SET level_1 = level_1 + ? WHERE user_id = ?";
        try {
            connect = DatabaseManager.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, 1); // Increment by 1 for a correct answer
            prepare.setString(2, username); // Assuming username is set correctly
            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Score updated successfully");
            } else {
                System.out.println("Score update failed. User may not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources if necessary
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Setter for username to be called when the username is set from UsernameController
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    @FXML
    protected void BackClick() {
        try {
            Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javaminiproject/StartGame.fxml")));
            Scene menuScene = new Scene(menuRoot);
            Stage stage = (Stage) helloButton.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setFullScreen(true); // Set the stage to be maximized
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
