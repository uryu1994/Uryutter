package application;

import util.TwitterUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "View/MainView.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                    getClass().getResource("View/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Uryutter");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        TwitterUtil.twitterStream.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
