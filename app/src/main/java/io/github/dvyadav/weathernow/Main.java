package io.github.dvyadav.weathernow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);    
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // setting application icon
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/weatherIcons/Partly-Cloudy-Day.gif")));
    
        // loading FXML file
        Parent root  = FXMLLoader.load(getClass().getResource("MainWeatherInfoScene.fxml"));

        root.setStyle("-fx-background-radius: 10");

        Scene scene = new Scene(root, 700, 538);
        primaryStage.setScene(scene);
        primaryStage.setTitle("WeatherNow");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}