package uryutter.application;

import java.io.IOException;
import twitter4j.Status;
import twitter4j.TwitterException;
import uryutter.util.TwitterUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * タイムラインのパネルを一つずつ制御するクラス
 * 
 * @author prices_over
 *
 */
public class TweetPaneController extends ListCell<Status> {

    @FXML
    private AnchorPane theColumn;

    @FXML
    private Label userName;

    @FXML
    private Label userId;

    @FXML
    private ImageView userIcon;

    @FXML
    private Label tweetContent;

    @FXML
    private Button reply;

    @FXML
    private Button favorite;

    @FXML
    private SVGPath favSvg;

    @FXML
    private Button retweet;

    @FXML
    private SVGPath rtSvg;

    private Status status;

    /**
     * タイムラインのパネルを制御するクラスのコンストラクタ
     * 
     * @param status 受信したステータス
     */
    public TweetPaneController(Status status) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TweetPane.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.status = status;
        userName.setText(status.getUser().getName());
        userId.setText("@"+status.getUser().getScreenName());
        tweetContent.setText(status.getText());
        userIcon.setImage(new Image(status.getUser().getBiggerProfileImageURL()));
        setFavoriteMark(status);

        setGraphic(getTheColumn());
    }

    /**
     * お気に入りを登録/解除します
     * 
     * @param ev マウスイベント
     */
    public void onFavorite(MouseEvent ev) {
        try {
            if(!status.isFavorited()) {
                MainViewController.mainViewController.updateTimeLine(
                        TwitterUtil.getTwitter().createFavorite(status.getId()));
            } else {
                MainViewController.mainViewController.updateTimeLine(
                        TwitterUtil.getTwitter().destroyFavorite(status.getId()));
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    /**
     * ツイートの詳細ページを生成します
     * 
     * @param e マウスイベント
     */
    @FXML
    public void createTweetFullPane(MouseEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FullTweetPane.fxml"));
        try {
            loader.load();
            Parent root = loader.getRoot();
            TweetFullPaneController tweetFullPane = loader.getController();

            Scene scene = new Scene(root);
            scene.getStylesheets().setAll("/styles/application.css");
            Stage fullTweetStage = new Stage(StageStyle.DECORATED);

            fullTweetStage.setScene(scene);
            fullTweetStage.setResizable(false);
            tweetFullPane.setItems(status);
            tweetFullPane.setStage(fullTweetStage);

            fullTweetStage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * お気に入りに登録してるならアイコンを黄色マーク、
     * していないならアイコンを灰色のままにします
     * 
     * @param status 確認するステータス
     */
    private void setFavoriteMark(Status status) {
        if(status.isFavorited()) {
            favSvg.setFill(Paint.valueOf("yellow"));
        } else {
            favSvg.setFill(Paint.valueOf("c6c6c6"));
        }
    }

    public AnchorPane getTheColumn() {
        return theColumn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
