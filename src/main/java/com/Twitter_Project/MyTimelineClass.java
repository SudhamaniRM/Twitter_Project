package com.Twitter_Project;

import twitter4j.*;
import twitter4j.TwitterException;
import java.util.List;

public class MyTimelineClass {
    public static String[] myTimeline(){
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        String str[] = new String[statuses.size()];
        int i = 0;
        for (Status st : statuses) {
            str[i] = String.join("--------->",st.getUser().getName(), st.getText());
            i++;
        }
        return str;
    }
}
