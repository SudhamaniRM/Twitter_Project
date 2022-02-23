/**
 * This package contains resources classes.
 */
package com.Twitter_Project.resources;

import com.Twitter_Project.models.SendResponse;
import com.Twitter_Project.models.TwitterResponse;
import com.Twitter_Project.services.TwitterImplement;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public class TwitterResources {
    /**
     * Logger is used to log messages to user.
     */
    private final Logger logger = LoggerFactory.getLogger(TwitterResources.class);
    /**
     * It Autowired the twitterimplementation class.
     */
    @Autowired
    private TwitterImplement twitterImplement;

    /**
     * Used for test cases.
     *
     * @param twitterImplement is a object of Twitter Implement Class.
     */
    public TwitterResources(TwitterImplement twitterImplement) {
        this.twitterImplement = twitterImplement;
    }

    /**
     * @return used to return new TwitterImplement().
     */
    @Bean
    public TwitterImplement getTwitterImplement() {
        return new TwitterImplement();
    }

    /**
     * getTimeline method used to fetch tweets which is returned from TwitterImplement.myTimeline().
     *
     * @return used to return tweets as response.
     */
    @RequestMapping("/getTimeline")
    public Response getTimeline() {
        logger.info("Retrieving Latest Tweets");
        return Response.ok(twitterImplement.myTimeline()).build();
    }

    /**
     * getFilterTweets method used to fetch filtered tweets from TwitterImplement.getFilteredTweets().
     *
     * @return used to return filtered tweets as response.
     */
    @RequestMapping("/filteredTweets/{searchKey}")
    public Response getFilterTweets(@PathVariable String searchKey) {
        List<TwitterResponse> tweets = twitterImplement.getFilteredTweets(searchKey);
        return Response.ok(tweets).build();
    }

    /**
     * postTweet method used to give response on post tweet to user timeline.
     *
     * @param request used to get message which has to be posted.
     * @return used to return response based on successful or unsuccessful post of tweet.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/postTweet")
    public ResponseEntity<SendResponse> postTweet(@RequestBody Request request) {
        String msg = request.getMsg();
        if (StringUtil.isEmpty(msg)) {
            logger.warn("Provide a valid tweet!");
            return new ResponseEntity(new SendResponse("Invalid!Please enter a valid tweet"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            try {
                Status status = twitterImplement.myTweet(msg);
                if (status.getText().equals(msg)) {
                    logger.info("Tweet posted successfully");
                    return new ResponseEntity(new SendResponse("Successfully posted tweet!"), new HttpHeaders(), HttpStatus.OK);
                } else {
                    logger.info("Unsuccessful!!, Tweet has not been posted");
                    return new ResponseEntity(new SendResponse("Resquest Incomplete"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (TwitterException exc) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Request Incomplete", exc);
            }
        }
    }
}
