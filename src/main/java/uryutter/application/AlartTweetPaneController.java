package uryutter.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 通知用のパネルのコントローラ
 * 
 * @author prices_over
 *
 */
public class AlartTweetPaneController extends Stage {
    
    private int num;
    private Stage stage;
    
    @FXML
    private Pane pane;
    
    @FXML
    private ImageView userImage;
    
    @FXML
    private Label userName;
    
    @FXML
    private Label userScreenName;
    
    @FXML
    private Label tweet;
    
    @FXML
    protected void closePane(MouseEvent e) {
        AlartManager.getInstance().hideAlart(num);
    }

    public ImageView getUserImage() {
        return userImage;
    }

    public void setUserImage(ImageView userImage) {
        this.userImage = userImage;
    }

    public Label getUserName() {
        return userName;
    }

    public void setUserName(Label userName) {
        this.userName = userName;
    }

    public Label getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(Label userScreenName) {
        this.userScreenName = userScreenName;
    }

    public Label getTweet() {
        return tweet;
    }

    public void setTweet(Label tweet) {
        this.tweet = tweet;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
    
}
