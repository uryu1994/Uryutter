package uryutter.util;

import javafx.application.Platform;
import uryutter.application.AlartManager;
import uryutter.application.MainViewController;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserStreamAdapter;

/**
 * ツイッターストリーム関係のUtilクラス
 *
 * @author prices_over
 *
 */
public class StreamUtil extends UserStreamAdapter {

    @Override
    public void onStatus(Status status) {
        Platform.runLater(() -> {
            MainViewController.mainViewController.homeTimeLine_O.add(0, status);
            // メンション通知
            if (TwitterUtil.getMyId().equals(status.getInReplyToScreenName())) {
                MainViewController.mainViewController.mentionList_O.add(0, status);
                Platform.runLater(() -> {
                    AlartManager.getInstance().createMentionAlart(status);
                });
            }
            // リツイート通知
            if (status.isRetweet()) {
                if (status.getRetweetedStatus().getUser()
                        .getScreenName().equals(TwitterUtil.getMyId())) {
                    Platform.runLater(() -> {
                        AlartManager.getInstance().createRTAlart(status);
                    });
                }
            }
        });
    }

    @Override
    public void onFavorite(User source, User target, Status favoritedStatus) {
        super.onFavorite(source, target, favoritedStatus);

        // 自分自身のツイートがファボられたとき
        if (target.getScreenName().equals(TwitterUtil.getMyId())) {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    AlartManager.getInstance().createFavAlart(favoritedStatus, source);
                }
            });
        }
    }
}
