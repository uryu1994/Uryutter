package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OAuthInfo implements Serializable {
    
    private final String consumer = "4unt2uyB69wKnVCtfbkZEJGV4";                                  
    private final String consumer_Secret = "nH4pRZOejxIrq33fLJIKBlPaG55GIDjN374epraAJhqS8nyoqx";  
    private String accessToken;                                                                  
    private String accessToken_Secret;
    
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getAccessToken_Secret() {
        return accessToken_Secret;
    }
    public void setAccessToken_Secret(String accessToken_Secret) {
        this.accessToken_Secret = accessToken_Secret;
    }
    public String getConsumer() {
        return consumer;
    }
    public String getConsumer_Secret() {
        return consumer_Secret;
    }

}
