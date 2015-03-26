package uryutter.model;

import java.io.Serializable;

/**
 * @author prices_over
 *
 */
@SuppressWarnings("serial")
public class OAuthInfo implements Serializable {
    
    private final String consumer = "4unt2uyB69wKnVCtfbkZEJGV4";                                  
    private final String consumer_Secret = "nH4pRZOejxIrq33fLJIKBlPaG55GIDjN374epraAJhqS8nyoqx";  
    private String accessToken;                                                                  
    private String accessToken_Secret;
    
    /**
     * アクセストークンを返す
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }
    
    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    /**
     * トークンシークレットを返す
     * @return トークンシークレット
     */
    public String getAccessToken_Secret() {
        return accessToken_Secret;
    }
    
    /**
     * @param accessToken_Secret the accessToken_Secret to set
     */
    public void setAccessToken_Secret(String accessToken_Secret) {
        this.accessToken_Secret = accessToken_Secret;
    }
    
    /**
     * コンシューマキーを返す
     * @return コンシューマキー
     */
    public String getConsumer() {
        return consumer;
    }
    
    /**
     * コンシューマキーを返す
     * @return コンシューマシークレット
     */
    public String getConsumer_Secret() {
        return consumer_Secret;
    }

}
