package com.runner;

import com.config.TwitterConfig;
import com.Twitter_Project.TwitterResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class TwitterRunner extends Application<TwitterConfig>{
    public static void main(String[] args) throws Exception {
        new TwitterRunner().run(args);
    }
    @Override
    public void run(TwitterConfig configuration,Environment environment) {
        environment.jersey().register(new TwitterResources());
    }
}
