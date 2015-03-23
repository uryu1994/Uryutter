package uryutter.util;

import java.util.List;

import javafx.scene.image.Image;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author prices_over
 *
 */
public class TwitterUtil {

    public static TwitterStream twitterStream;

    private static String myName;
    private static String myId;
    private static Image myIcon;
    
    Status status;
    private static Twitter twitter;
    private List<Status> list;
    private List<Status> mentionList;

    public TwitterUtil() {

        try {            
            Configuration conf = new ConfigurationBuilder().build();
            OAuthAuthorization oauth = new OAuthAuthorization(conf);
            oauth.setOAuthConsumer(OAuthUtil.getoAuthInfo().getConsumer(), OAuthUtil.getoAuthInfo().getConsumer_Secret());
            oauth.setOAuthAccessToken(OAuthUtil.getAccessToken());
            twitter = new TwitterFactory().getInstance(oauth);
            list = twitter.getHomeTimeline();
            mentionList = twitter.getMentionsTimeline();
            twitterStream = new TwitterStreamFactory().getInstance(oauth);
            twitterStream.addListener(new StreamUtil());
            twitterStream.user();
            myName = twitter.verifyCredentials().getName();
            myId = twitter.verifyCredentials().getScreenName();
            myIcon = new Image(twitter.verifyCredentials().getBiggerProfileImageURL());
        } catch (IllegalStateException | TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * ツイート処理をする
     * @param t ツイート内容がかかれたツイート
     */
    public void tweet(String t) {
        try {
            status = twitter.updateStatus(t);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    public static String getMyName() {
        return myName;
    }

    public static String getMyId() {
        return myId;
    }

    public static Image getMyIcon() {
        return myIcon;
    }

    public static Twitter getTwitter() {
        return twitter;
    }

    public static void setTwitter(Twitter twitter) {
        TwitterUtil.twitter = twitter;
    }

}
