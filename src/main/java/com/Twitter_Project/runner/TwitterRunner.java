/**
 * This runner package includes TwitterRunner class.
 */
package com.Twitter_Project.runner;

import com.Twitter_Project.config.TwitterConfig;
import com.Twitter_Project.resources.TwitterResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.Twitter_Project.resources",
        "com.Twitter_Project.services",
        "com.Twitter_Project.config", "com.Twitter_Project.runner"})
@EnableCaching
public class TwitterRunner extends Application<TwitterConfig> {
    /**
     * Logger is used log message in this class.
     * displays message once successfully project runs and main method gets called.
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterRunner.class);
    private TwitterResources twitterResources;
    private TwitterConfig twitterConfig;
    private Environment environment;

    /**
     * Used for test case.
     */
    public TwitterRunner(final TwitterConfig twitterConfig, final Environment environment) {
        this.twitterConfig = twitterConfig;
        this.environment = environment;
    }

    public TwitterRunner() {

    }

    /**
     * main() used to call run().
     *
     * @param args arguments given to run().
     */
    public static void main(final String[] args) {
        LOGGER.info("Executing run() method");
        SpringApplication.run(TwitterRunner.class, args);
    }

    /**
     * run() used to run the class and calls TwitterResources class.
     *
     * @param twitterConfig calls TwitterConfig class.
     * @param environment   sets environment to run project.
     */
    @Override
    public void run(final TwitterConfig twitterConfig, final Environment environment) {
        LOGGER.info("Processing a call to Resources class");
        environment.jersey().register(twitterResources);
    }
}
