package uryutter.application;

import twitter4j.Status;
import javafx.scene.control.ListCell;

/**
 * タイムラインが更新されたときに呼び出されるクラス
 * 
 * @author prices_over
 *
 */
public class TweetCell extends ListCell<Status> {
    
    /**
     * タイムラインが更新された時に行を追加します
     * 
     * @param status ステータス
     * @param empty ステータスが空かを判定
     */
    @Override
    public void updateItem(Status status, boolean empty) {
        super.updateItem(status, empty);
        if(status != null) {
            TweetPaneController newTweet = new TweetPaneController(status);
            setGraphic(newTweet.getTheColumn());
        }
    }

}
