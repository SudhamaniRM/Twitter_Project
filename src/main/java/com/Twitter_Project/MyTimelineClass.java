package com.Twitter_Project;

import twitter4j.*;
import twitter4j.TwitterException;
import java.util.List;

public class MyTimelineClass {
    public static String[] myTimeline() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = twitter.getHomeTimeline();
        String str[] = new String[statuses.size()];
        int i=0;
        for (Status st : statuses) {
            str[i] = String.join("--------->",st.getUser().getName(), st.getText());
            i++;
        }
        return str;
    }
}