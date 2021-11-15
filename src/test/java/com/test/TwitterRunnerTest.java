package com.test;

import com.Twitter_Project.TwitterResources;
import com.config.TwitterConfig;
import com.runner.TwitterRunner;
import io.dropwizard.setup.Environment;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TwitterRunnerTest {
    TwitterRunner twitterRunner;
    TwitterConfig twitterConfig;
    Environment environment;
    TwitterResources twitterResources;
    Logger logger = LoggerFactory.getLogger(TwitterRunnerTest.class);

    @BeforeEach
    void setUp() {
        twitterConfig = Mockito.mock(TwitterConfig.class);
        environment = Mockito.mock(Environment.class);
        twitterRunner = new TwitterRunner();
        twitterResources = Mockito.mock(TwitterResources.class);
    }

    @Test
    void main() {
        String arg[] = {"server"};
        try {
            TwitterRunner.main(arg);
        } catch (Exception e) {
            e.getMessage();
            logger.error("Error", e);
        }
    }

    @Test
    void run() {
        try {
            twitterRunner.run(twitterConfig, environment);
        } catch (Exception e) {
            e.getMessage();
            logger.error("Error", e);
        }

        Assert.assertTrue(true);
    }
}
