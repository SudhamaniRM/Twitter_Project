package com.test;

import com.Twitter_Project.models.SendResponse;
import com.Twitter_Project.resources.Request;
import com.Twitter_Project.resources.TwitterResources;
import com.Twitter_Project.services.TwitterImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterResourcesTest {
    TwitterResources twitterResources;
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
        Response actualResponse = twitterResources.getTimeline();
        Assert.assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void testCase_postTweetFromTwitterResources_emptyTweet() {
        Request request = new Request("");
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.BAD_REQUEST);
        ResponseEntity<SendResponse> actualResponse = twitterResources.postTweet(request);
        Assert.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
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
        ResponseEntity<SendResponse> actual = twitterResources.postTweet(request);
        ResponseEntity expected = new ResponseEntity(HttpStatus.OK);
        Assert.assertEquals(expected.getStatusCode(), actual.getStatusCode());
    }
    @Test(expected = ResponseStatusException.class)
    public void testCase_BadRequestException_error() throws TwitterException {
        Request request = new Request("hello");
        when(twitterImplement.myTweet("hello")).thenThrow(ResponseStatusException.class);
        ResponseEntity<SendResponse> actual = twitterResources.postTweet(request);
    }
}
