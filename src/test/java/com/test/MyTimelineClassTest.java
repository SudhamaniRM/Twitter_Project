package com.test;

import com.Twitter_Project.MyTimelineClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MyTimelineClassTest {
    Twitter twitter;
    MyTimelineClass myTimelineClass;

    @Before
    public void setUp() {
        myTimelineClass = new MyTimelineClass();
    }

    @Test
    public void testCase_myTimelineFromMyTimelineClass_successCase() throws TwitterException {
        twitter = TwitterFactory.getSingleton();
        List<Status> status = twitter.getHomeTimeline();
        String[] str = new String[status.size()];
        int i = 0;
        for (Status st : status) {
            str[i] = String.join("--------->", st.getUser().getName(), st.getText());
            i++;
        }
        List<String> expectedTweets = Arrays.asList(str);
        List<String> actualTweets = Arrays.asList(myTimelineClass.myTimeline());
        Assert.assertEquals(expectedTweets, actualTweets);
    }
}
