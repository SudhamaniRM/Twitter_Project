package com.Twitter_Project.resources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class Request {
    private String msg;

    /**
     * Takes the tweet message from the postman json body and sends to postTweet() to post a tweet on timeline.
     *
     * @param msg is tweet to be posted.
     */
    public Request(String msg) {
        this.msg = msg;
    }

    public Request() {
    }
}
