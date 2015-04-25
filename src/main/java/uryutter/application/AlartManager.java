package uryutter.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;
import twitter4j.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 通知のパネルを管理するクラス
 * 
 * @author prices_over
 *
 */
public class AlartManager {

    private static AlartManager instance;
    private static List<AlartTweetPaneController> alarts;

    private AlartManager() {
        alarts = new ArrayList<AlartTweetPaneController>();
    }

    /**
     * リツイート通知用のパネルを生成します
     * 
     * @param status リツイートされたツイートのステータス
     */
    public void createRTAlart(Status status) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlartTweetPane.fxml"));
        try {
            loader.load();
            Parent root = loader.getRoot();
            AlartTweetPaneController alartP = loader.getController();

            Scene scene = new Scene(root);
            Stage alartS = new Stage(StageStyle.TRANSPARENT);

            alartS.setScene(scene);
            alartS.setResizable(false);

            alartP.getUserName().setText(status.getUser().getName());
            alartP.getUserScreenName().setText("@"+status.getUser().getScreenName());
            alartP.getTweet().setText(status.getText());
            alartP.getUserImage().setImage(new Image(status.getUser().getBiggerProfileImageURL()));
            alartP.getPane().setStyle("-fx-background-color: lightseagreen;");
            alartP.setNum(alarts.size());
            alartP.setStage(alartS);

            alarts.add(alartP);
            showAlarts();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * お気に入り通知のパネルを生成します
     * 
     * @param status お気に入り登録されたツイートのステータス
     * @param favUser お気に入り登録したユーザ
     */
    public void createFavAlart(Status status, User favUser) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlartTweetPane.fxml"));
        try {
            loader.load();
            Parent root = loader.getRoot();
            AlartTweetPaneController alartP = loader.getController();

            Scene scene = new Scene(root);
            Stage alartS = new Stage(StageStyle.TRANSPARENT);

            alartS.setScene(scene);
            alartS.setResizable(false);

            alartP.getUserName().setText(favUser.getName());
            alartP.getUserScreenName().setText("@"+status.getUser().getScreenName());
            alartP.getTweet().setText(status.getText());
            alartP.getUserImage().setImage(new Image(favUser.getBiggerProfileImageURL()));
            alartP.getPane().setStyle("-fx-background-color: yellow;");
            alartP.setNum(alarts.size());
            alartP.setStage(alartS);

            alarts.add(alartP);
            showAlarts();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * メンション通知のパネルを生成します
     * 
     * @param status メンションツイートのステータス
     */
    public void createMentionAlart(Status status) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlartTweetPane.fxml"));
        try {
            loader.load();
            Parent root = loader.getRoot();
            AlartTweetPaneController alartP = loader.getController();

            Scene scene = new Scene(root);
            Stage alartS = new Stage(StageStyle.TRANSPARENT);

            alartS.setScene(scene);
            alartS.setResizable(false);

            alartP.getUserName().setText(status.getUser().getName());
            alartP.getUserScreenName().setText("@"+status.getUser().getScreenName());
            alartP.getTweet().setText(status.getText());
            alartP.getUserImage().setImage(new Image(status.getUser().getBiggerProfileImageURL()));

            alartP.getPane().setStyle("-fx-background-color: skyblue;");

            alartP.setNum(alarts.size());
            alartP.setStage(alartS);

            alarts.add(alartP);
            showAlarts();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAlarts() {
        for(int i=0;i<alarts.size();i++) {
            Stage stage = alarts.get(i).getStage();
            stage.setX(1150);
            stage.setY(50+90*i);
            stage.show();

        }
    }

    public void hideAlart(int num) {
        alarts.get(num).getStage().hide();
        alarts.remove(num);
        reNumbering(num);
        showAlarts();
    }

    private void reNumbering(int num) {
        for(int i=num; i<alarts.size(); i++) {
            alarts.get(i).setNum(i);
        }
    }

    public static AlartManager getInstance() {
        if(instance == null) {
            instance = new AlartManager();
        }
        return instance;
    }

    public static void setInstance(AlartManager instance) {
        AlartManager.instance = instance;
    }

    public static List<AlartTweetPaneController> getAlarts() {
        return alarts;
    }

    public static void setAlarts(List<AlartTweetPaneController> alarts) {
        AlartManager.alarts = alarts;
    }

}
