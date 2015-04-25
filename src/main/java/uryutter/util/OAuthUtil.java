package uryutter.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import uryutter.application.OAuthController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uryutter.model.OAuthInfo;

/**
 * OAuth認証用のUtilクラス
 * 
 * @author prices_over
 *
 */
public class OAuthUtil {

    private static OAuthUtil instance;

    private static OAuthInfo oAuthInfo;
    private static RequestToken requestToken;
    private static AccessToken accessToken;

    /**
     * 生成されているキーを読み込みます
     * (無ければOAuth認証するWebViewを表示し、キーを生成します)
     * 
     * @param primaryStage
     * @throws Exception
     */
    public void readOAuthInfo(Stage primaryStage) throws Exception {
        oAuthInfo = new OAuthInfo();
        File file = new File(".key.dat");
        
        //-- キーがなければ生成し、あれば読み込んでアクセストークンを生成し、OAuth認証を行います --//
        if(!file.exists()) {
            createAccessToken();
        } else {
            FileInputStream fis = new FileInputStream(".key.dat");
            ObjectInputStream in = new ObjectInputStream(fis);
            oAuthInfo = (OAuthInfo) in.readObject();
            in.close();
            fis.close();
            accessToken = new AccessToken(oAuthInfo.getAccessToken(), oAuthInfo.getAccessToken_Secret());
            
            //-- ホーム画面の生成 --//
            try {
                Parent root = FXMLLoader.load(getClass().getResource(
                        "/fxml/MainView.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(
                        getClass().getResource("/styles/application.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.setTitle("Uryutter");
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * アクセストークンを生成します
     */
    public void createAccessToken() {
        try {
            TwitterFactory.getSingleton().setOAuthConsumer(oAuthInfo.getConsumer(), oAuthInfo.getConsumer_Secret());
            requestToken = TwitterFactory.getSingleton().getOAuthRequestToken();
        } catch (TwitterException e1) {
            e1.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OAuth.fxml"));
        
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        OAuthController oAuthController = loader.getController();
        Parent root = loader.getRoot();
        oAuthController.setUrl(requestToken.getAuthenticationURL());
        loader.setController(oAuthController);

        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    /**
     * 生成したOAuth認証情報をオブジェクトごと保存します
     * 
     * @throws Exception
     */
    public static void writeOAuthInfo() throws Exception {
        FileOutputStream fout = new FileOutputStream(".key.dat");
        ObjectOutputStream oout = new ObjectOutputStream(fout);

        oout.writeObject(oAuthInfo);
        oout.close();
        fout.close();
    }

    public static OAuthInfo getoAuthInfo() {
        return oAuthInfo;
    }

    public static void setoAuthInfo(OAuthInfo oAuthInfo) {
        OAuthUtil.oAuthInfo = oAuthInfo;
    }

    public static RequestToken getRequestToken() {
        return requestToken;
    }

    public static OAuthUtil getInstance() {
        return instance;
    }

    public static void setInstance(OAuthUtil instance) {
        OAuthUtil.instance = instance;
    }

    public static AccessToken getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(AccessToken accessToken) {
        OAuthUtil.accessToken = accessToken;
    }

    public static void setRequestToken(RequestToken requestToken) {
        OAuthUtil.requestToken = requestToken;
    }

}
