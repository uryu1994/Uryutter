package util;

import application.MainViewController;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.UserStreamAdapter;

public class StreamUtil extends UserStreamAdapter {

    public void onStatus(Status status) {
        super.onStatus(status);
        MainViewController.observableList.add(0, status);
        
        // 昆布が健全と言い張っているときにリプを出す
        if(status.getUser().getScreenName()==("KNB623")&&status.getText().contains("健全")) {
            try {
                TwitterFactory.getSingleton().updateStatus("@KNB623 ダウト");
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void onFavorite(User source, User target, Status favoritedStatus) {
        super.onFavorite(source, target, favoritedStatus);

        // メカヲタ君にふぁぼられたらその旨のツイートをする
        if(source.getScreenName()==("mecaota")&&target.getScreenName()==("prices_over")
                &&favoritedStatus.isFavorited()) {
            try {
                TwitterFactory.getSingleton().updateStatus("メカヲタ君にふぁぼられたぞーーー");
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
