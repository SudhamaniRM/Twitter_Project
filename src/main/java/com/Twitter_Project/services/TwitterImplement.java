package com.Twitter_Project.services;

import com.Twitter_Project.config.TwitterConfig;
import com.Twitter_Project.models.TwitterResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TwitterImplement {
    public static Logger logger = LoggerFactory.getLogger(TwitterImplement.class);
    TwitterConfig twitterConfig;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Twitter twitter;
    TwitterResponse twitterResponse;


    public TwitterImplement(TwitterFactory twitterFactory) {
        this.twitterFactory = twitterFactory;
        this.twitter = twitterFactory.getInstance();
    }

    public TwitterImplement() {
        twitterConfig = new TwitterConfig();
        configurationBuilder = twitterConfig.configurationBuilder();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }

    public Status myTweet(String msg) throws TwitterException {
        Status status = twitter.updateStatus(msg);
        return status;
    }

    public ArrayList<TwitterResponse> myTimeline() {
        String twitterHandle;
        String name;
        String message;
        String profileImageUrl;
        Date createdAt = null;

        ArrayList<TwitterResponse> arrayList = new ArrayList<>();
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < statuses.size(); i++) {
        Status s = statuses.get(i);
        profileImageUrl = s.getUser().getProfileImageURL();
        name = s.getUser().getName();
        twitterHandle =s.getUser().getScreenName();
        message = s.getText();
        createdAt = s.getCreatedAt();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(createdAt);
        twitterResponse = new TwitterResponse(message,twitterHandle,name,profileImageUrl,date);
        arrayList.add(twitterResponse);
        }
        return arrayList;
    }
}
