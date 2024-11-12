package com.example.javaminiproject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    private MediaView mediaView;
    private MediaPlayer videoMediaPlayer;
    private MediaPlayer audioMediaPlayer;
    private Stage stage; // To hold the current stage
    public void initialize() {
        // Set up media loading and playback
        String videoPath = getClass().getResource("/com/example/javaminiproject/welcome.mp4").toString();
        String audioPath = getClass().getResource("/com/example/javaminiproject/bg.mp3").toString();
        loadMedia(videoPath, true);
        loadMedia(audioPath, false);
    }
    private void loadMedia(String path, boolean isVideo) {
        if (path != null) {
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            if (isVideo) {
                videoMediaPlayer = mediaPlayer;
                mediaView.setMediaPlayer(videoMediaPlayer);
                videoMediaPlayer.setOnEndOfMedia(() -> videoMediaPlayer.seek(Duration.ZERO));
                videoMediaPlayer.setAutoPlay(true); // Start playing the video automatically
            } else {
                audioMediaPlayer = mediaPlayer;
                audioMediaPlayer.setOnEndOfMedia(() -> audioMediaPlayer.seek(Duration.ZERO));
                audioMediaPlayer.setAutoPlay(true); // Start playing the audio automatically
            }
        }
    }
    @FXML
    protected void onHelloButtonClick() {
        try {
            Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javaminiproject/GameMenu.fxml")));
            Scene menuScene = new Scene(menuRoot);
            stage = (Stage) mediaView.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setFullScreen(true); // Set the stage to be maximized
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void bindMediaViewSize(Scene scene) {
        mediaView.fitWidthProperty().bind(scene.widthProperty());
        mediaView.fitHeightProperty().bind(scene.heightProperty());
    }
}
