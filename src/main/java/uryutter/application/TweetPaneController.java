package uryutter.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.UserMentionEntity;
import uryutter.util.TwitterUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button retweet;

    private Status status;

    public TweetPaneController(Status status) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TweetPane.fxml"));
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.status = status;
        userName.setText(status.getUser().getName());
        userId.setText("@"+status.getUser().getScreenName());
        tweetContent.setText(status.getText());
        userIcon.setImage(new Image(status.getUser().getBiggerProfileImageURL()));
        setGraphic(getTheColumn());
    }

    public void onFavorite(MouseEvent ev) {
        try {
            status = TwitterUtil.getTwitter().createFavorite(status.getId());
            System.out.println("favorited");
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            try {
                status = TwitterUtil.getTwitter().destroyFavorite(status.getId());
                System.out.println("Unfavorited");
            } catch (TwitterException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

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

            tweetFullPane.getUserName().setText(status.getUser().getName());
            tweetFullPane.getUserId().setText("@"+status.getUser().getScreenName());
            tweetFullPane.getTweetContent().setText(status.getText());
            tweetFullPane.getUserIcon()
            .setImage(new Image(status.getUser().getBiggerProfileImageURL()));
            tweetFullPane.setStatus(status);
            
            tweetFullPane.setStage(fullTweetStage);

            fullTweetStage.show();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
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
