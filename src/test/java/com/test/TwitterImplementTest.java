package com.test;

import com.Twitter_Project.resources.Request;
import com.Twitter_Project.services.TwitterImplement;
import com.config.TwitterConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterImplementTest {
    TwitterImplement twitterImplement;
    TwitterFactory twitterFactory;
    Twitter twitter;
    TwitterConfig twitterConfig;
    Request request;

    @Before
    public void setUp() {
        request = new Request();
        twitterConfig = mock(TwitterConfig.class);
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterImplement = new TwitterImplement(twitterFactory);
    }

    @Test
    public void testCase_myTimelineFromTwitterImplement_successCase() throws TwitterException {
        ResponseList<Status> responseList = mock(ResponseList.class);
        Status status1 = mock(Status.class);
        Status status2 = mock(Status.class);
        Status status3 = mock(Status.class);
        when(responseList.size()).thenReturn(3);
        when(responseList.get(0)).thenReturn(status1);
        when(status1.getText()).thenReturn("Tweet1");
        when(responseList.get(1)).thenReturn(status2);
        when(status2.getText()).thenReturn("Tweet2");
        when(responseList.get(2)).thenReturn(status3);
        when(status3.getText()).thenReturn("Tweet3");
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        List<String> expected = Arrays.asList("Tweet1", "Tweet2", "Tweet3");
        List<String> actual = twitterImplement.myTimeline();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCase_myTweetFromTwitterImplement_successCase() throws TwitterException {
        Status expected = mock(Status.class);
        String message = "My Tweet!!";
        when(twitter.updateStatus(message)).thenReturn(expected);
        Status actual = twitterImplement.myTweet(message);
        Assert.assertEquals(expected, actual);
    }
}
