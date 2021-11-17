package com.test;

import com.Twitter_Project.resources.Request;
import com.Twitter_Project.resources.TwitterResources;
import com.Twitter_Project.services.TwitterImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterResourcesTest {
    TwitterResources twitterResources;
    Logger logger = LoggerFactory.getLogger(TwitterResourcesTest.class);
    TwitterImplement twitterImplement;

    @Before
    public void setUp() {
        twitterImplement = mock(TwitterImplement.class);
        twitterResources = new TwitterResources(twitterImplement);
    }

    @Test
    public void testCase_getTimelineFromTwitterResources_successCase() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("One");
        arrayList.add("Two");
        Response expectedResponse = Response.ok(arrayList).build();
        when(twitterImplement.myTimeline()).thenReturn(arrayList);
        Response actualResponse = twitterResources.getTimeline();
        Assert.assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void testCase_postTweetFromTwitterResources_emptyTweet() {
        Request request = new Request("");
        Response expectedResponse = Response.status(400, "Invalid!!,Please enter a valid tweet").build();
        Response actualResponse = twitterResources.postTweet(request);
        Assert.assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void testCase_postTweetFromTwitterResources_sendTweet() throws TwitterException {
        String msg = "My Tweet";
        Request request = new Request();
        request.setMsg(msg);
        String tweet = request.getMsg();
        Status status = mock(Status.class);
        when(status.getText()).thenReturn(tweet);
        when(twitterImplement.myTweet(tweet)).thenReturn(status);
        Response actual = twitterResources.postTweet(request);
        Response expected = Response.status(200, "Successfully Tweeted").build();
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
    }
}
