package com.Twitter_Project.runner;

import com.Twitter_Project.config.TwitterConfig;
import com.Twitter_Project.resources.TwitterResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.Twitter_Project.resources", "com.Twitter_Project.services", "com.Twitter_Project.config", "com.Twitter_Project.runner"})
public class TwitterRunner extends Application<TwitterConfig> {
    public static Logger logger = LoggerFactory.getLogger(TwitterRunner.class);
    TwitterResources twitterResources;
    TwitterConfig twitterConfig;
    Environment environment;

    public TwitterRunner(TwitterConfig twitterConfig, Environment environment) {
        this.twitterConfig = twitterConfig;
        this.environment = environment;
    }

    public TwitterRunner() {

    }

    public static void main(String[] args) {
        logger.info("Executing run() method");
        SpringApplication.run(TwitterRunner.class, args);
    }

    @Override
    public void run(TwitterConfig twitterConfig, Environment environment) {
        logger.info("Processing a call to Resources class");
        environment.jersey().register(twitterResources);
    }
}
