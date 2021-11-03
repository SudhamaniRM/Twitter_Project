package com.Twitter_Project;

import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Twitter;

public class MyTweetClass {
    public static Status myTweet(String msg) {
        Twitter twitter = TwitterFactory.getSingleton();
        Status status = null;
        try {
            status = twitter.updateStatus(msg);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return status;
    }
}
