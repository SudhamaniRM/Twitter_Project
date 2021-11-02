package com.Twitter_Project;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class twitterRunner extends Application<twitterConfig>{
    public static void main(String[] args) throws Exception {
        new twitterRunner().run(args);
    }
    @Override
    public void run(twitterConfig configuration,Environment environment) {
        environment.jersey().register(new twitterResources());
    }
}