package util;

import application.MainViewController;
import twitter4j.Status;
import twitter4j.User;
import twitter4j.UserStreamAdapter;

public class StreamUtil extends UserStreamAdapter {

    public void onStatus(Status status) {
        super.onStatus(status);
        MainViewController.homeTimeLine_O.add(0, status);

        if(new String("prices_over").equals(status.getInReplyToScreenName())) {
            MainViewController.mentionList_O.add(0, status);
        }
    }

    public void onFavorite(User source, User target, Status favoritedStatus) {
        super.onFavorite(source, target, favoritedStatus);
    }

}
