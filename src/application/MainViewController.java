package application;

import java.net.URL;
import java.util.ResourceBundle;

import twitter4j.Status;
import util.TwitterUtil;
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
    public static ObservableList<Status> observableList = FXCollections.observableArrayList();

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
    protected void tweetAction(ActionEvent ev) {
        String tweetText = newTweet.getText();
        twitterUtil.tweet(tweetText);
        newTweet.setText("");
    }
    
    @FXML
    protected void tweetButtonAction(ActionEvent ev) {
        if(newTweet.getText().compareTo(null)!=0) {
            tweetButton.setDisable(true);
        } else {
            tweetButton.setDisable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        twitterUtil = new TwitterUtil();

        userName.setText(twitterUtil.getMyName());
        userId.setText("@"+twitterUtil.getMyId());
        userIcon.setImage(twitterUtil.getMyIcon());
        
        observableList = homeTimeLine.getItems();
        observableList.setAll(twitterUtil.getList());
        homeTimeLine.setItems(observableList);
        homeTimeLine
        .setCellFactory(new Callback<ListView<Status>, ListCell<Status>>() {

            @Override
            public ListCell<Status> call(ListView<Status> param) {
                // TODO Auto-generated method stub
                return new TweetCell();
            }

        });
        
        mainViewController = this;

    }

}
