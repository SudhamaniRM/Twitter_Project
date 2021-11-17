package com.runner;

import com.Twitter_Project.MyTimelineClass;
import com.config.TwitterConfig;
import com.Twitter_Project.TwitterResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterRunner extends Application<TwitterConfig>{
    TwitterConfig twitterConfig;
    Environment environment;
    public static Logger logger= LoggerFactory.getLogger(MyTimelineClass.class);
    public TwitterRunner(TwitterConfig twitterConfig, Environment environment) {
        this.twitterConfig= twitterConfig;
        this.environment=environment;
    }
    public TwitterRunner() {

    }
    public static void main(String[] args) throws Exception {
        logger.info("Executing run() method");
        new TwitterRunner().run(args);
    }
    @Override
    public void run(TwitterConfig twitterConfig,Environment environment) {
        logger.info("Processing a call to Resources class");
        environment.jersey().register(new TwitterResources());
    }
}
