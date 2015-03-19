package util;

import java.util.List;

import javafx.scene.image.Image;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.Configuration;

public class TwitterUtil {

    public static TwitterStream twitterStream;

    private static final String oAuthConsumerKey = "";
    private static final String oAuthConsumerSecret = "";
    private static final String oAuthAccessToken = "";
    private static final String oAuthAccessTokenSecret = "";

    private String myName;
    private String myId;
    private Image myIcon;

    Configuration config;
    public static TwitterFactory tf;
    Twitter twitter;
    Status status;
    private List<Status> list;

    public TwitterUtil() {

        config = new ConfigurationBuilder()
        .setDebugEnabled(true)
        .setOAuthConsumerKey(oAuthConsumerKey)
        .setOAuthConsumerSecret(oAuthConsumerSecret)
        .setOAuthAccessToken(oAuthAccessToken)
        .setOAuthAccessTokenSecret(oAuthAccessTokenSecret)
        .build();

        tf = new TwitterFactory(config);
        twitter = tf.getInstance();
        
        try {
            list = tf.getInstance().getHomeTimeline();
        } catch (TwitterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        twitterStream = new TwitterStreamFactory(config).getInstance();
        twitterStream.addListener(new StreamUtil());
        twitterStream.user();

        try {
            myName = twitter.verifyCredentials().getName();
            myId = twitter.verifyCredentials().getScreenName();
            myIcon = new Image(twitter.verifyCredentials().getBiggerProfileImageURL());
        } catch (IllegalStateException | TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void tweet(String t) {
        try {
            status = twitter.updateStatus(t);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public String getMyName() {
        return myName;
    }

    public String getMyId() {
        return myId;
    }

    public Image getMyIcon() {
        return myIcon;
    }

    public List<Status> getList() {
        return list;
    }

    public void setList(List<Status> list) {
        this.list = list;
    }

}
