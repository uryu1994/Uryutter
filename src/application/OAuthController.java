package application;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import util.OAuthUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OAuthController {

    private String pincode;
    private String url;

    @FXML
    private PasswordField pin;

    @FXML
    private WebView webview;

    @FXML
    protected void pinAuth(ActionEvent ev) {
        pincode = pin.getText();
        if(pincode.length() > 0) {
            try {
                AccessToken access = TwitterFactory.getSingleton().getOAuthAccessToken(OAuthUtil.getRequestToken(), pincode);
                OAuthUtil.getoAuthInfo().setAccessToken(access.getToken());
                OAuthUtil.getoAuthInfo().setAccessToken_Secret(access.getTokenSecret());
                try {
                    OAuthUtil.setAccessToken(access);
                    OAuthUtil.writeOAuthInfo();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Stage primaryStage = new Stage(StageStyle.DECORATED);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource(
                            "MainView.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(
                            getClass().getResource("application.css").toExternalForm());
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Uryutter");
                    primaryStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }            
        }else {
            pin.setText("");
        }
    }

    public String getPincode() {
        return pincode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        webview.getEngine().load(getUrl());
    }

}
