package com.Twitter_Project;

import com.config.TwitterConfig;
import twitter4j.*;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyTimelineClass {

    TwitterConfig twitterConfig;

    public  MyTimelineClass(){

    }
    public MyTimelineClass(TwitterConfig twitterConfig) {

        this.twitterConfig=twitterConfig;
    }

    public static String[] myTimeline(){
        Logger logger= LoggerFactory.getLogger(MyTimelineClass.class);
        TwitterConfig twitterConfig =new TwitterConfig();
        ConfigurationBuilder configurationBuilder = twitterConfig.configurationBuilder();
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
        } catch (TwitterException e) {
            logger.info("Just a stack trace, nothing to worry about", e);
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
