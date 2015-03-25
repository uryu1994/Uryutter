package uryutter.application;

import java.io.IOException;
import twitter4j.Status;
import twitter4j.TwitterException;
import uryutter.util.TwitterUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
