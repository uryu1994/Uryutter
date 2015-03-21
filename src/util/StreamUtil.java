package util;

import javafx.application.Platform;
import application.AlartManager;
import application.MainViewController;
import twitter4j.Status;
import twitter4j.User;
import twitter4j.UserStreamAdapter;

public class StreamUtil extends UserStreamAdapter {

    public void onStatus(Status status) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                MainViewController.homeTimeLine_O.add(0, status);
                if(new String("prices_over").equals(status.getInReplyToScreenName())) {
                    MainViewController.mentionList_O.add(0, status);
                    Platform.runLater(() -> {
                        AlartManager.getInstance().createAlart(status);
                    });
                }
            }
        });
    }

    public void onFavorite(User source, User target, Status favoritedStatus) {
        super.onFavorite(source, target, favoritedStatus);
    }

}
