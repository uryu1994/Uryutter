package model;

import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class OAuthInfo {

    private static final String Consumer_Key = "4unt2uyB69wKnVCtfbkZEJGV4";
    private static final String Consumer_Key_Secret = "nH4pRZOejxIrq33fLJIKBlPaG55GIDjN374epraAJhqS8nyoqx";

    private AccessToken accessToken;
    private RequestToken requestToken;
    private String pin;
    private String url;
    
    public OAuthInfo() {
        accessToken = null;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
    public RequestToken getRequestToken() {
        return requestToken;
    }
    public void setRequestToken(RequestToken requestToken) {
        this.requestToken = requestToken;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public static String getConsumerKey() {
        return Consumer_Key;
    }
    public static String getConsumerKeySecret() {
        return Consumer_Key_Secret;
    }   

}
