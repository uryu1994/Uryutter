package uryutter.application;

import twitter4j.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TweetFullPaneController extends Stage {

    private Stage stage;
    public ObservableList<Status> mentionList_O = FXCollections.observableArrayList();
    
    @FXML
    private Button reply;

    @FXML
    private Button favorite;

    @FXML
    private Button retweet;

    @FXML
    private Label userName;

    @FXML
    private Label userId;

    @FXML
    private ImageView userIcon;

    @FXML
    private Label tweetContent;
    
    @FXML
    private ListView<Status> replyList;
    
    private Status status;
    
    @FXML
    protected void pushReply(ActionEvent ev) {
        MainViewController.mainViewController.newTweet.setText(userId.getText()+" ");
        MainViewController.mainViewController.setInReplyToStatusId(status.getId());
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
