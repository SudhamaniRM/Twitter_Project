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

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterResourcesTest {
    TwitterResources twitterResources;
    Twitter twitter;
    MyTimelineClass myTimelineClass;
    MyTweetClass myTweetClass;
    Request request;
    MockedStatic<MyTimelineClass> myTimelineClassMockedStatic = Mockito.mockStatic(MyTimelineClass.class);
    MockedStatic<MyTweetClass> myTweetClassMockedStatic = Mockito.mockStatic(MyTweetClass.class);

    @Before
    public void setUp() {
        myTimelineClass = mock(MyTimelineClass.class);
        myTweetClass = mock(MyTweetClass.class);
        twitterResources = new TwitterResources(myTimelineClass);
        request = new Request();
        twitter = TwitterFactory.getSingleton();

    }

    @Test
    public void testCase_getTimelineFromTwitterResources_successCase() throws TwitterException {
        String str[] = myTimelineClass.myTimeline();
        myTimelineClassMockedStatic.when(MyTimelineClass::myTimeline).thenReturn(str);
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
        String msg = "";
        request.setMsg(msg);
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
}
