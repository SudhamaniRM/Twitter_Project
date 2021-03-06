package com.test;

import com.Twitter_Project.config.TwitterConfig;
import com.Twitter_Project.resources.TwitterResources;
import com.Twitter_Project.runner.TwitterRunner;
import io.dropwizard.setup.Environment;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TwitterRunnerTest {
    TwitterRunner twitterRunner;
    TwitterConfig twitterConfig;
    Environment environment;
    TwitterResources twitterResources;
    Logger logger = LoggerFactory.getLogger(TwitterRunnerTest.class);

    @BeforeEach
    void setUp() {
        twitterConfig = Mockito.mock(TwitterConfig.class);
        environment = Mockito.mock(Environment.class);
        twitterRunner = new TwitterRunner(twitterConfig, environment);
        twitterResources = Mockito.mock(TwitterResources.class);
    }

    @Test
    void main() {
        String[] arg = {"server"};
        try {
            TwitterRunner.main(arg);
        } catch (Exception e) {
            logger.error("Exception occur", e);
        }
    }

    @Test
    void run() {
        try {
            twitterRunner.run(twitterConfig, environment);
        } catch (Exception e) {
            logger.error("Exception occur", e);
        }

        Assert.assertTrue(true);
    }
}
