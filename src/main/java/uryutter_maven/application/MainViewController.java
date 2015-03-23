package uryutter_maven.application;

import java.net.URL;
import java.util.ResourceBundle;

import twitter4j.Status;
import uryutter_maven.util.TwitterUtil;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class MainViewController implements Initializable {

    public static MainViewController mainViewController;

    public TwitterUtil twitterUtil;

    public static ObservableList<Status> homeTimeLine_O = FXCollections.observableArrayList();
    public static ObservableList<Status> mentionList_O = FXCollections.observableArrayList();

    @FXML
    public TextArea newTweet;

    @FXML
    public ImageView userIcon;

    @FXML
    public Label userName;

    @FXML
    public Label userId;

    @FXML
    public Button tweetButton;

    @FXML
    public ListView<Status> homeTimeLine;

    @FXML
    public ListView<Status> mentionList;

    @FXML
    protected void tweetAction(ActionEvent ev) {
        String tweetText = newTweet.getText();
        if(!tweetText.equals("")) {
            twitterUtil.tweet(tweetText);
            newTweet.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        twitterUtil = new TwitterUtil();

        userName.setText(TwitterUtil.getMyName());
        userId.setText("@"+TwitterUtil.getMyId());
        userIcon.setImage(TwitterUtil.getMyIcon());

        newTweet.textProperty().addListener(new InvalidationListener() {
            
            // 入力検知(何も入ってなければツイートボタンを無効)
            @Override
            public void invalidated(Observable observable) {
                // TODO Auto-generated method stub
                if(!newTweet.getText().equals("")) {
                    tweetButton.setDisable(false);
                } else {
                    tweetButton.setDisable(true);
                }
            }

        });

        homeTimeLine_O = homeTimeLine.getItems();
        homeTimeLine_O.setAll(twitterUtil.getList());
        homeTimeLine.setItems(homeTimeLine_O);
        homeTimeLine
        .setCellFactory(new Callback<ListView<Status>, ListCell<Status>>() {
            @Override
            public ListCell<Status> call(ListView<Status> param) {
                // TODO Auto-generated method stub
                return new TweetCell();
            }
        });

        mentionList_O = mentionList.getItems();
        mentionList_O.setAll(twitterUtil.getMentionList());
        mentionList.setItems(mentionList_O);
        mentionList.setCellFactory(new Callback<ListView<Status>, ListCell<Status>>() {

            @Override
            public ListCell<Status> call(ListView<Status> param) {
                // TODO Auto-generated method stub
                return new TweetCell();
            }
        });
        mainViewController = this;
    }

}
