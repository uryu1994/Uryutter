package uryutter.util;

import twitter4j.Status;

public class DateUtil {
    
    /**
     * 投稿したツイートの相対時間を返します
     * @param status
     * @return 相対時間
     */
    public static String getTweetDate(Status status) {
        long dateTimeFrom = status.getCreatedAt().getTime();
        long dateTimeTo = System.currentTimeMillis();
        
        long dayDiff = (dateTimeTo - dateTimeFrom) / 1000;
        
        if(dayDiff < 60) {
            return dayDiff + "s";
        } else if(dayDiff / 60 < 60) {
            return dayDiff / 60 + "m";
        } else if(dayDiff / (60 * 60) < 24) {
            return dayDiff / (60 * 60) + "h";
        } else {
            return dayDiff / (60 * 60 * 24) + "d";
        }  
    }
    
}
