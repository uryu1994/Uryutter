package uryutter.application;

import uryutter.util.OAuthUtil;
import uryutter.util.TwitterUtil;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Uryutterのメインクラス
 * 
 * @author prices_over
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        // --OAuth認証-- //
        try {
            OAuthUtil oAuthUtil = new OAuthUtil();
            oAuthUtil.readOAuthInfo(primaryStage);
        } catch (Exception e1) {
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
