package model;

/**
 * @author prices_over
 *
 */
public class Tweet {
    
    private String userId;
    private String userName;
    private String tweetContent;
    private int favoriteCount;
    private int retweetedCount;
    
    public Tweet(){
    }
    
    /**
     * @param userId ユーザID
     * @param userName ユーザ名
     * @param tweetContent ツイート内容
     * @param favoriteCount お気に入り数
     * @param retweetedCount リツイート数
     */
    public Tweet(String userId, String userName, String tweetContent,
            int favoriteCount, int retweetedCount) {
        this.userId = userId;
        this.userName = userName;
        this.tweetContent = tweetContent;
        this.favoriteCount = favoriteCount;
        this.retweetedCount = retweetedCount;
    }
    
    /**
     * @return ユーザIDを返す
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param userId ユーザIDをセット
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * @return ユーザ名を返す
     */
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getTweetContent() {
        return tweetContent;
    }
    
    public void setTweetContent(String tweetContent) {
        this.tweetContent = tweetContent;
    }
    
    public int getFavoriteCount() {
        return favoriteCount;
    }
    
    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
    
    public int getRetweetedCount() {
        return retweetedCount;
    }
    
    public void setRetweetedCount(int retweetedCount) {
        this.retweetedCount = retweetedCount;
    }
    
}
