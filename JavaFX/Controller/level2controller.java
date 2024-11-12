package com.example.javaminiproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class level2controller {

    @FXML
    private Label questionLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Button helloButton;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField answerField;
    @FXML
    private Button submitButton;
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private StackPane stackPane;

    private Random random = new Random();
    private int level = 2;
    private int score = 0;
    private int questionIndex = 0;
    private int correctAnswer = 0;
    private int timeRemaining = 420; // Timer duration in seconds
    private Timer timer;
    private static final int NUMBER_OF_QUESTIONS = 5;
    private String username; // To store the username for database update
    private Connection connect;
    private PreparedStatement prepare;

    public void initialize() {
        setupBackgroundImage();
        startLevel();
        startTimer();
    }
    private UsernameController h1 = new UsernameController();
    public void setUsernameFromLevel1() {
        username = h1.getUsername();
        System.out.println(username);

    }

    private void setupBackgroundImage() {
        backgroundImageView.setImage(new Image(getClass().getResourceAsStream("/level2bg.png")));
        backgroundImageView.fitWidthProperty().bind(stackPane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stackPane.heightProperty());
    }

    private void startLevel() {
        questionIndex = 0;
        generateQuestion();
    }

    private void generateQuestion() {
        int a = random.nextInt(10);
        int b = random.nextInt(10);
        int c = random.nextInt(10);
        String question;

        int choice = random.nextInt(8);
        switch (choice) {
            case 0:
                question = "What is: " + a + " OR " + b + " OR " + c + "?";
                correctAnswer = orOperation(a, b, c);
                break;
            case 1:
                question = "What is: " + a + " AND " + b + " AND " + c + "?";
                correctAnswer = andOperation(a, b, c);
                break;
            case 2:
                question = "What is: (" + a + " AND " + b + ") OR " + c + "?";
                correctAnswer = r(a, b, c);
                break;
            case 3:
                question = "What is: (" + a + " OR " + b + ") AND " + c + "?";
                correctAnswer = r2(a, b, c);
                break;
            case 4:
                question = "What is: " + a + " XOR " + b + " OR " + c + "?";
                correctAnswer = exclusiveOr(a, b, c);
                break;
            case 5:
                question = "What is: " + a + " XOR (" + b + " AND " + c + ")?";
                correctAnswer = exclusiveAnd(a, b, c);
                break;
            case 6:
                question = "What is: (" + a + " AND " + b + ") XOR " + c + "?";
                correctAnswer = andExclusive(a, b, c);
                break;
            case 7:
                question = "What is: (" + a + " OR " + b + ") XOR " + c + "?";
                correctAnswer = orExclusive(a, b, c);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + choice);
        }

        questionLabel.setText(question);
    }

    // Level 2 Operation Methods
    private int orOperation(int a, int b, int c) {
        return a | b | c;
    }

    private int andOperation(int a, int b, int c) {
        return a & b & c;
    }

    private int r(int a, int b, int c) {
        return (a & b) | c;
    }

    private int r2(int a, int b, int c) {
        return (a | b) & c;
    }

    private int exclusiveOr(int a, int b, int c) {
        return a ^ b | c;
    }

    private int exclusiveAnd(int a, int b, int c) {
        return a ^ (b & c);
    }

    private int andExclusive(int a, int b, int c) {
        return (a & b) ^ c;
    }

    private int orExclusive(int a, int b, int c) {
        return (a | b) ^ c;
    }

    @FXML
    private void handleSubmit() {
        try {
            int userAnswer = Integer.parseInt(answerField.getText().trim());
            if (userAnswer == correctAnswer) {
                score++;
                scoreLabel.setText(String.valueOf(score));
                questionLabel.setText("Correct! The answer was: " + correctAnswer);
                updateScoreInDatabase();
            } else {
                questionLabel.setText("Wrong! The correct answer was: " + correctAnswer);
            }
            answerField.clear();

            questionIndex++;
            if (questionIndex < NUMBER_OF_QUESTIONS) {
                generateQuestion();
            } else {
                handleEndGame();
            }
        } catch (NumberFormatException e) {
            questionLabel.setText("Please enter a valid number!");
        }
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
                } else {
                    Platform.runLater(level2controller.this::handleEndGame);
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

    public void setUsername(String username) {
        this.username = username;
    }


    private void updateScoreInDatabase() {
        setUsernameFromLevel1();

        if (username == null || username.isEmpty()) {
            System.out.println("Username is not set. Cannot update score.");
            return;
        }
        String sql = "UPDATE userinfo SET level_2 = level_2 + 1 WHERE user_id = ?";
        try {
            connect = DatabaseManager.getConnection();
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);

            int rowsUpdated = prepare.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Score updated successfully.");
            } else {
                System.out.println("Score update failed. User may not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleEndGame() {
        questionLabel.setText("Game Over! Your final score is: " + score);
        submitButton.setDisable(true);
        answerField.setDisable(true);
        if (timer != null) {
            timer.cancel();
        }
    }


    @FXML
    public void BackClick() {
        try {
            Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javaminiproject/StartGame.fxml")));
            Scene menuScene = new Scene(menuRoot);
            Stage stage = (Stage) helloButton.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setFullScreen(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
