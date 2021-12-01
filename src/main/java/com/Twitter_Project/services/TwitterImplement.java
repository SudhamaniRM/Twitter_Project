package com.Twitter_Project.services;

import com.Twitter_Project.config.TwitterConfig;
import com.Twitter_Project.models.TwitterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@CacheConfig(cacheNames={"allTweets","filteredTweets"})
@Service
public class TwitterImplement {
    TwitterConfig twitterConfig;
    ConfigurationBuilder configurationBuilder;
    TwitterFactory twitterFactory;
    Twitter twitter;
    TwitterResponse twitterResponse;

    @Autowired
    public TwitterImplement() {
        twitterConfig = new TwitterConfig();
        configurationBuilder = twitterConfig.configurationBuilder();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();

    }
    public TwitterImplement(TwitterFactory twitterFactory, TwitterResponse twitterResponse) {
        this.twitterFactory = twitterFactory;
        this.twitterResponse = twitterResponse;
        this.twitter = twitterFactory.getInstance();
    }
    @Cacheable(cacheNames={"allTweets"})
    @CacheEvict(cacheNames={"allTweets"},allEntries = true)
    public Status myTweet(String msg) throws TwitterException {
        Status status = twitter.updateStatus(msg);
        return status;
    }
    @Cacheable(cacheNames={"allTweets"})
    public ArrayList<TwitterResponse> myTimeline() {
        ArrayList<TwitterResponse> Tweets = new ArrayList<>();
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < statuses.size(); i++) {
            Status s = statuses.get(i);
            String profileImageUrl = s.getUser().getProfileImageURL();
            String name = s.getUser().getName();
            String twitterHandle = s.getUser().getScreenName();
            String message = s.getText();
            Date createdAt = s.getCreatedAt();
            Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = formatter.format(createdAt);
            twitterResponse = new TwitterResponse(message, twitterHandle, name, profileImageUrl, date);
            Tweets.add(twitterResponse);
        }
        return Tweets;
    }
    @Cacheable(cacheNames={"filteredTweets"})
    public List<TwitterResponse> getFilteredTweets(String tweets) {
        ArrayList<TwitterResponse> tweetList = myTimeline();
        int len = tweets.length();
        CharSequence charSequence = tweets.subSequence(0, len);
        List<TwitterResponse> filteredTweets = tweetList.stream().filter(t -> t.getMessage().contains(charSequence)).collect(Collectors.toList());
        return filteredTweets;
    }
}
