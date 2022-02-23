/**
 * This package contains configuration class.
 */
package com.Twitter_Project.config;

import io.dropwizard.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TwitterConfig extends Configuration {
    private final Logger logger = LoggerFactory.getLogger(TwitterConfig.class);
    /**
     * String filepath stores the .yml file which contains the authentication keys.
     */
    private final String filepath = "twitter4j.yml";
    private final Properties properties = new Properties();
    private FileInputStream fileInputStream;
    private String accessTokenSecret = "";
    private String consumerSecret = "";
    private String consumerKey = "";
    private String accessToken = "";

    {
        try {
            fileInputStream = new FileInputStream(filepath);
        } catch (FileNotFoundException e) {
            logger.error("Exception occur", e);
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Error in loading configuration", e);
        }
        accessTokenSecret = properties.getProperty("accessTokenSecret");
        consumerSecret = properties.getProperty("consumerSecret");
        consumerKey = properties.getProperty("consumerKey");
        accessToken = properties.getProperty("accessToken");
    }

    /**
     * ConfigurationBuilder class used to set all Authentication keys.
     *
     * @return returns the keys.
     */
    public ConfigurationBuilder configurationBuilder() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        return configurationBuilder;
    }
}
