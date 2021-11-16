package com.runner;

import com.config.TwitterConfig;
import com.Twitter_Project.TwitterResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class TwitterRunner extends Application<TwitterConfig>{
    TwitterConfig twitterConfig;
    Environment environment;

    public TwitterRunner(TwitterConfig twitterConfig, Environment environment) {
        this.twitterConfig= twitterConfig;
        this.environment=environment;
    }
    public TwitterRunner() {

    }
    public static void main(String[] args) throws Exception {
        new TwitterRunner().run(args);
    }
    @Override
    public void run(TwitterConfig twitterConfig,Environment environment) {
        environment.jersey().register(new TwitterResources());
    }
}
