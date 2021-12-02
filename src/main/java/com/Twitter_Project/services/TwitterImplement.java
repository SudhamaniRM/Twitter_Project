/**
 * This package contains the Implementation class.
 */
package com.Twitter_Project.services;

import com.Twitter_Project.config.TwitterConfig;
import com.Twitter_Project.models.TwitterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
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

@CacheConfig(cacheNames = {"allTweets", "filteredTweets"})
@Service
public class TwitterImplement {
    public ConfigurationBuilder configurationBuilder;
    private TwitterConfig twitterConfig;
    private final TwitterFactory twitterFactory;
    private final Twitter twitter;
    private TwitterResponse twitterResponse;

    @Autowired
    public TwitterImplement() {
        twitterConfig = new TwitterConfig();
        configurationBuilder = twitterConfig.configurationBuilder();
        twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();

    }

    /**
     * Used for test case.
     *
     * @param twitterFactory
     * @param twitterResponse
     */
    public TwitterImplement(TwitterFactory twitterFactory, TwitterResponse twitterResponse) {
        this.twitterFactory = twitterFactory;
        this.twitterResponse = twitterResponse;
        this.twitter = twitterFactory.getInstance();
    }

    /**
     * myTweet() used to post tweet in user timeline.
     *
     * @param msg is a tweet which is to be posted.
     * @return returns the status to resource class.
     * @throws TwitterException when there is unsuccessful post of tweet.
     */
    @Cacheable(cacheNames = {"allTweets"})
    @Scheduled(fixedRate = 2000)
    @CacheEvict(cacheNames = {"allTweets"}, allEntries = true)
    public Status myTweet(final String msg) throws TwitterException {
        Status status = twitter.updateStatus(msg);
        return status;
    }

    /**
     * myTimeline() used to get tweets from user timeline.
     *
     * @return returns tweets to resources class.
     */
    @Cacheable(cacheNames = {"allTweets"})
    public ArrayList<TwitterResponse> myTimeline() {
        ArrayList<TwitterResponse> tweets = new ArrayList<>();
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
            tweets.add(twitterResponse);
        }
        return tweets;
    }

    /**
     * getFilteredTweets() used to get filtered tweets from user timeline.
     *
     * @param searchTweet is used to search in a list of tweets.
     * @return returns filtered tweets.
     */
    @Cacheable(cacheNames = {"filteredTweets"})
    public List<TwitterResponse> getFilteredTweets(final String searchTweet) {
        ArrayList<TwitterResponse> tweetList = myTimeline();
        int len = searchTweet.length();
        CharSequence charSequence = searchTweet.subSequence(0, len);
        List<TwitterResponse> filteredTweets = tweetList.stream().filter(t -> t.getMessage().contains(charSequence)).collect(Collectors.toList());
        return filteredTweets;
    }
}
