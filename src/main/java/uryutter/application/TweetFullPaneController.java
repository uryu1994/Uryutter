package uryutter.application;

import twitter4j.Status;
import twitter4j.TwitterException;
import uryutter.util.TwitterUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import static uryutter.util.DateUtil.getTweetDate;

/**
 * ツイートの詳細ウィンドウを制御するクラス
 * 
 * @author prices_over
 *
 */
public class TweetFullPaneController extends Stage {

    private Stage stage;
    public ObservableList<Status> mentionList_O = FXCollections.observableArrayList();

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

    @FXML
    private Label userName;

    @FXML
    private Label userId;

    @FXML
    private Label tweetTime;
    
    @FXML
    private ImageView userIcon;

    @FXML
    private Label tweetContent;

    @FXML
    private ListView<Status> replyList;

    private Status status;

    /**
     * リプライボタンが押された時の動作
     * @param ev
     */
    @FXML
    protected void pushReply(ActionEvent ev) {
        MainViewController.mainViewController.newTweet.setText(userId.getText()+" ");
        MainViewController.mainViewController.setInReplyToStatusId(status.getId());
    }

    /**
     * favoriteボタンが押された時の動作
     * @param ev
     */
    @FXML
    public void pushFavorite(ActionEvent ev) {
        try {
            if(!status.isFavorited()) {
                setStatus(TwitterUtil.getTwitter().createFavorite(status.getId()));
            } else {
                setStatus(TwitterUtil.getTwitter().destroyFavorite(status.getId()));
            }
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        /* --お気に入り登録したツイートのタイムラインを更新します-- */
        setItems(status);
        MainViewController.mainViewController.updateTimeLine(status);
    }

    /**
     * アイテムをセットします
     * @param status
     */
    public void setItems(Status status) {
        userName.setText(status.getUser().getName());
        userId.setText("@"+status.getUser().getScreenName());
        tweetTime.setText(getTweetDate(status));
        tweetContent.setText(status.getText());
        userIcon.setImage(new Image(status.getUser().getBiggerProfileImageURL()));
        setStatus(status);

        setFavoriteMark(status);
    }

    /**
     * お気に入りが登録されていれば色を塗り、されてなければ色を消します
     * @param status ツイート
     */
    public void setFavoriteMark(Status status) {
        if(status.isFavorited()) {
            favSvg.setFill(Paint.valueOf("yellow"));
            System.out.println("Favorited");
        } else {
            favSvg.setFill(Paint.valueOf("c6c6c6"));
            System.out.println("UnFavorited");
        }
    }

    public Label getUserName() {
        return userName;
    }

    public void setUserName(Label userName) {
        this.userName = userName;
    }

    public Label getUserId() {
        return userId;
    }

    public void setUserId(Label userId) {
        this.userId = userId;
    }

    public ImageView getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(ImageView userIcon) {
        this.userIcon = userIcon;
    }

    public Label getTweetContent() {
        return tweetContent;
    }

    public void setTweetContent(Label tweetContent) {
        this.tweetContent = tweetContent;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
