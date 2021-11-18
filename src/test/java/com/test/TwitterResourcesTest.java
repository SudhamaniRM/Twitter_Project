package com.test;

import com.Twitter_Project.MyTimelineClass;
import com.Twitter_Project.MyTweetClass;
import com.Twitter_Project.Request;
import com.Twitter_Project.TwitterResources;
import org.eclipse.jetty.util.StringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import javax.ws.rs.core.Response;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TwitterResourcesTest {
    TwitterResources twitterResources;
    Twitter twitter;
    MyTimelineClass myTimelineClass;
    Request request;
    @Before
    public void setUp() {
        twitterResources = new TwitterResources(myTimelineClass);
        request = new Request();
        twitter = TwitterFactory.getSingleton();
    }

    @Test
    public void testCase_getTimelineFromTwitterResources_successCase() throws TwitterException {
        MockedStatic<MyTimelineClass> myTimelineClassMockedStatic = Mockito.mockStatic(MyTimelineClass.class);
        String tweets[] = myTimelineClass.myTimeline();
        myTimelineClassMockedStatic.when(MyTimelineClass::myTimeline).thenReturn(tweets);
        List<Status> status = twitter.getHomeTimeline();
        String[] str1 = new String[status.size()];
        int i = 0;
        for (Status st : status) {
            str1[i] = String.join("--------->", st.getUser().getName(), st.getText());
            i++;
        }
        Response expectedResponse = Response.ok(str1).build();
        Response actualResponse = twitterResources.getTimeline();
        Assert.assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void testCase_postTweetFromTwitterResources_emptyTweet() {
        String message = "";
        request.setMsg(message);
        String expectedTweet = request.getMsg();
        String actualTweet = "";
        Response actualResponse = null;
        Response expectedResponse = null;
        if (StringUtil.isEmpty(expectedTweet) && StringUtil.isEmpty(actualTweet)) {
            expectedResponse = Response.status(400, "Invalid!!,Please enter a valid tweet").build();
            actualResponse = twitterResources.postTweet(request);
        }
        Assert.assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    @Test
    public void testCase_postTweetFromTwitterResources_sendTweet() throws TwitterException {
        String message = "nice day...";
        Status status = MyTweetClass.myTweet(message);
        MockedStatic<MyTweetClass> myTweetClassMockedStatic = Mockito.mockStatic(MyTweetClass.class);
        myTweetClassMockedStatic.when(() -> MyTweetClass.myTweet(message)).thenReturn(status);
        String expectedTweet = request.getMsg();
        String actualTweet = "nice day...";
        Response actualResponse = null;
        Response expectedResponse = null;
        if (status.getText().equals(expectedTweet) && status.getText().equals(actualTweet) ) {
            expectedResponse=  Response.status(200, "Successfully Tweeted").build();
            actualResponse = twitterResources.postTweet(request);
        } else {
            expectedResponse =  Response.status(400, "Request Incomplete!!").build();
            actualResponse = twitterResources.postTweet(request);
        }
            Assert.assertEquals(expectedResponse.getStatus(),actualResponse.getStatus());
        }
    }

