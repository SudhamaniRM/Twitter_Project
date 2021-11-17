package com.test;

import com.Twitter_Project.MyTweetClass;
import com.Twitter_Project.Request;
import com.config.TwitterConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MyTweetClassTest {
    TwitterConfig twitterConfig;
    MyTweetClass myTweetClass;
    Request request;
    Logger logger = LoggerFactory.getLogger(MyTweetClassTest.class);

    @Before
    public void setUp() {
        twitterConfig = mock(TwitterConfig.class);
        myTweetClass = new MyTweetClass();
        request = new Request();
    }

    @Test
    public void testCase_myTweetFromMyTweetClass_successCase() {
        request.setMsg("flower!");
        String expectedTweet = request.getMsg();
        Status status = null;
        try {
            status = MyTweetClass.myTweet(expectedTweet);
        } catch (TwitterException e) {
            logger.error("Exception occur",e);
        }
        String actualTweet = status.getText();
        Assert.assertEquals(expectedTweet, actualTweet);
    }

    @Test
    public void testCase_myTweetFromMyTweetClass_NoTweetsCase() {
        request.setMsg("");
        String expectedTweet = request.getMsg();
        Status status = null;
        try {
            status = MyTweetClass.myTweet(expectedTweet);
        } catch (TwitterException e) {
            logger.error("Exception occur",e);
        }
        int actualLength = 0;
        int expectedLength = 0;
        String actualTweet = "";
        if (status != null) {
            actualTweet = status.getText();
        }
        if (status == null) {
            expectedLength = expectedTweet.length();
            actualLength = 0;
        } else {
            expectedLength = expectedTweet.length();
            actualLength = actualTweet.length();
        }
        Assert.assertEquals(expectedLength, actualLength);
    }
}
