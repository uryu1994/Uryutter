package util;

import java.util.List;

import model.OAuthInfo;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.Configuration;

public class TwitterUtil {

    public static TwitterStream twitterStream;
    public OAuthInfo oAuthInfo;

    private String myName;
    private String myId;
    private Image myIcon;

    public static TwitterFactory tf;
    Twitter twitter;
    Status status;
    private List<Status> list;
    private List<Status> mentionList;

    public TwitterUtil() {
        tf = new TwitterFactory();
        twitter = tf.getInstance();
        
        oAuthInfo = new OAuthInfo();
        
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(OAuthInfo.getConsumerKey(), OAuthInfo.getConsumerKeySecret());
        
        try {
            oAuthInfo.setRequestToken(twitter.getOAuthRequestToken());
            oAuthInfo.setUrl(oAuthInfo.getUrl());
        } catch (TwitterException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        
        WebView webview = new WebView();
        webview.getEngine().load(oAuthInfo.getUrl());

        try {
            list = twitter.getHomeTimeline();
        } catch (TwitterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        twitterStream = new TwitterStreamFactory().getInstance(oAuthInfo.getAccessToken());

        try {
            mentionList = tf.getInstance().getMentionsTimeline();
        } catch (TwitterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        twitterStream = new TwitterStreamFactory().getInstance();
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

    public List<Status> getMentionList() {
        return mentionList;
    }

    public void setMentionList(List<Status> mentionList) {
        this.mentionList = mentionList;
    }

}
