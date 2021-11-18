package com.config;

import io.dropwizard.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TwitterConfig extends Configuration {
    Logger logger = LoggerFactory.getLogger(TwitterConfig.class);
    String filepath = "twitter4j.yml";
    String accessTokenSecret = "";
    String consumerSecret = "";
    String consumerKey = "";
    String accessToken = "";
    Properties properties = new Properties();
    FileInputStream fileInputStream;

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
