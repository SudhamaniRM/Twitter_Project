package com.test;

import com.Twitter_Project.config.TwitterConfig;
import com.Twitter_Project.models.TwitterResponse;
import com.Twitter_Project.resources.Request;
import com.Twitter_Project.resources.TwitterResources;
import com.Twitter_Project.services.TwitterImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterImplementTest {
    TwitterImplement twitterImplement;
    TwitterResources twitterResources;
    TwitterFactory twitterFactory;
    Twitter twitter;
    TwitterConfig twitterConfig;
    Request request;
    TwitterResponse twitterResponse;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String message = "Bangalore is one of my favourite places";
    String twitterHandle = "sudhamani_r_m";
    String name = "Sudhamani R M";
    String profileImageUrl = "http://Sudha/normal.jpg";
    Date createdAt;
    String date;

    {
        try {
            createdAt = dateFormat.parse("25-11-2021 07:23:36");
        } catch (ParseException E) {
            E.printStackTrace();
        }
        date = dateFormat.format(createdAt);
    }

    @Before
    public void setUp() {
        twitterResources = mock(TwitterResources.class);
        request = new Request();
        twitterConfig = mock(TwitterConfig.class);
        twitterFactory = mock(TwitterFactory.class);
        twitter = mock(Twitter.class);
        twitterResponse = spy(new TwitterResponse(message, twitterHandle, name, profileImageUrl, date));
        when(twitterFactory.getInstance()).thenReturn(twitter);
        twitterImplement = new TwitterImplement(twitterFactory, twitterResponse);
    }

    @Test
    public void testCase_fetchTweet_successCase() throws TwitterException {
        Status s1 = mock(Status.class);
        User user = mock(User.class);
        ResponseList<Status> responseList = mock(ResponseList.class);
        ArrayList<TwitterResponse> expectedList =new ArrayList<>();
        when(responseList.size()).thenReturn(1);
        when(responseList.get(0)).thenReturn(s1);
        when(s1.getUser()).thenReturn(user);
        when(s1.getUser().getProfileImageURL()).thenReturn(profileImageUrl);
        when(s1.getUser().getName()).thenReturn(name);
        when(s1.getUser().getScreenName()).thenReturn(twitterHandle);
        when(s1.getText()).thenReturn(message);
        when(s1.getCreatedAt()).thenReturn(createdAt);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        expectedList.add(twitterResponse);
        ArrayList<TwitterResponse> actualList = twitterImplement.myTimeline();
        Assert.assertEquals(expectedList,actualList);
    }

    @Test
    public void testCase_myTweetFromTwitterImplement_successCase() throws TwitterException {
        Status expected = mock(Status.class);
        String message = "My Tweet!!";
        when(twitter.updateStatus(message)).thenReturn(expected);
        Status actual = twitterImplement.myTweet(message);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getFilteredTweets_SuccessCase() {
        ArrayList<String> str = new ArrayList<String>();
        str.add("Bangalore is very good place");
        str.add("Have a good day");
        str.add("It was all just good clean fun.");
        str.add("It felt so good to be home.");
        when(twitterResources.getFilterTweets("good")).thenReturn(Response.ok(str).build());
        Response expectedTweet = Response.ok(str).build();
        Response actualTweet = twitterResources.getFilterTweets("good");
        Assert.assertEquals(expectedTweet.getEntity(), actualTweet.getEntity());
    }

    @Test
    public void noTweetMatchTest() throws TwitterException {
        ResponseList<Status> responseList = mock(ResponseList.class);
        when(responseList.size()).thenReturn(0);
        when(twitter.getHomeTimeline()).thenReturn(responseList);
        List<TwitterResponse> actual = twitterImplement.getFilteredTweets("cute");
        Assert.assertEquals(Arrays.asList(), actual);
    }
}
