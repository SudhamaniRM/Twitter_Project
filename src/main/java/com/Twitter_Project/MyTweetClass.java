package com.Twitter_Project;

import com.config.TwitterConfig;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Twitter;
import twitter4j.conf.ConfigurationBuilder;

public class MyTweetClass {
    public static Status myTweet(String msg) throws TwitterException {
        TwitterConfig twitterConfig =new TwitterConfig();
        ConfigurationBuilder configurationBuilder = twitterConfig.configurationBuilder();
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
        Status status = twitter.updateStatus(msg);
        return status;
    }
}
