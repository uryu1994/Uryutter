package application;

import util.OAuthUtil;
import util.TwitterUtil;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            OAuthUtil oAuthUtil = new OAuthUtil();
            oAuthUtil.readOAuthInfo(primaryStage);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void stop() {
        TwitterUtil.twitterStream.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }    
}
