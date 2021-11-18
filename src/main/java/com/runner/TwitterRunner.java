package com.runner;

import com.Twitter_Project.resources.TwitterResources;
import com.config.TwitterConfig;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterRunner extends Application<TwitterConfig> {
    public static Logger logger = LoggerFactory.getLogger(TwitterRunner.class);
    TwitterConfig twitterConfig;
    Environment environment;

    public TwitterRunner(TwitterConfig twitterConfig, Environment environment) {
        this.twitterConfig = twitterConfig;
        this.environment = environment;
    }

    public TwitterRunner() {

    }

    public static void main(String[] args) throws Exception {
        logger.info("Executing run() method");
        new TwitterRunner().run(args);
    }

    @Override
    public void run(TwitterConfig twitterConfig, Environment environment) {
        logger.info("Processing a call to Resources class");
        environment.jersey().register(new TwitterResources());
    }
}
