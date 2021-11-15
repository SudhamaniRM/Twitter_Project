package com.test;

import com.Twitter_Project.MyTimelineClass;
import com.config.TwitterConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyTimelineClassTest {
    TwitterConfig twitterConfig;
    MyTimelineClass myTimelineClass;
    Twitter twitter;

    @Before
    public void setUp() {
        twitterConfig= Mockito.mock(TwitterConfig.class);
        twitter= Mockito.mock(Twitter.class);
        myTimelineClass = new MyTimelineClass(twitterConfig);
    }

    @Test
    public void testCase_myTimelineFromMyTimelineClass_successCase() throws TwitterException {
        TwitterConfig twitterConfig =new TwitterConfig();
        ConfigurationBuilder configurationBuilder = twitterConfig.configurationBuilder();
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();
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
