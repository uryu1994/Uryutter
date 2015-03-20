package application;

import java.io.IOException;

import twitter4j.Status;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AlartTweetPaneController extends Stage {
    
    @FXML
    private ImageView userImage;
    
    @FXML
    private Label userName;
    
    @FXML
    private Label userScreenName;
    
    @FXML
    private Label tweet;
    
    public AlartTweetPaneController(Status status) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AlartTweetPane.fxml"));
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        userImage.setImage(new Image(status.getUser().getOriginalProfileImageURL()));
        userName.setText(status.getUser().getName());
        userScreenName.setText(status.getUser().getScreenName());
        tweet.setText(status.getText());
        
    }
    
}
