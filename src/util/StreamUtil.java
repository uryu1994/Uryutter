package util;

import application.MainViewController;
import twitter4j.Status;
import twitter4j.UserStreamAdapter;

public class StreamUtil extends UserStreamAdapter {
    
    public void onStatus(Status status) {
        super.onStatus(status);
        System.out.println(status.getUser().getScreenName());

        MainViewController.mainViewController.observableList.add(0, status);
    }
    
}
