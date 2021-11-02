package com.Twitter_Project;


import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Twitter;

public class MyTweetClass {
    public static boolean myTweet(String msg){
        Twitter twitter = TwitterFactory.getSingleton();
        try{
            twitter.updateStatus(msg);
            return true;
        } catch (TwitterException e) {
            return false;
        }
    }
}