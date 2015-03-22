package util;

import javafx.application.Platform;
import application.AlartManager;
import application.MainViewController;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.UserStreamAdapter;

public class StreamUtil extends UserStreamAdapter {

    public void onStatus(Status status) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                MainViewController.homeTimeLine_O.add(0, status);
                // メンション通知
                if(TwitterUtil.getMyId().equals(status.getInReplyToScreenName())) {
                    MainViewController.mentionList_O.add(0, status);
                    Platform.runLater(() -> {
                        AlartManager.getInstance().createAlart(status);
                    });
                }
                // リツイート通知
                if(status.isRetweet())
                    if(status.getRetweetedStatus().getUser()
                            .getScreenName().equals(TwitterUtil.getMyId())) {
                        Platform.runLater(() -> {
                            AlartManager.getInstance().createRTAlart(status);
                        });
                    }
            }
        });
    }

    public void onFavorite(User source, User target, Status favoritedStatus) {
        super.onFavorite(source, target, favoritedStatus);

        // 自分自身のツイートがファボられたとき
        if(target.getScreenName().equals(TwitterUtil.getMyId())) {
            Platform.runLater(() -> {
                AlartManager.getInstance().createFavAlart(favoritedStatus, source);
                // メカヲタくんにふぁぼられたとき
                if(source.getScreenName().equals("mecaota")) {
                    try {
                        TwitterFactory.getSingleton()
                        .updateStatus("メカヲタ君にふぁぼられたぞーーーーーwwwwwww");
                    } catch (TwitterException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
