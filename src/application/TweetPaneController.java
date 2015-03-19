package application;

import java.io.IOException;

import twitter4j.Status;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TweetPaneController extends ListCell<Status> {

    private Status status;
    
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

    public TweetPaneController(Status status) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TweetPane.fxml"));
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

    public AnchorPane getTheColumn() {
        return theColumn;
    }

}
