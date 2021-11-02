package com.Twitter_Project;

import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Twitter;

public class MyTweetClass {
    public static void myTweet(String msg) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        try{
            Status status = twitter.updateStatus(msg);
        } catch (TwitterException te){
            te.printStackTrace();
        }
    }
}