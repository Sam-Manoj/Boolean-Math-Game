package com.example.javaminiproject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javaminiproject/hello-view.fxml"));
        StackPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("LOGI-PIXEL");
        stage.setFullScreen(true);
        stage.show();
        // Bind the MediaView size after the scene is set
        HelloController controller = fxmlLoader.getController();
        controller.bindMediaViewSize(scene);
    }
    public static void main(String[] args) {
        launch();
    }
}
