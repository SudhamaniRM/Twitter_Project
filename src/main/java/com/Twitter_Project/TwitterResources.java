package com.Twitter_Project;

import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TwitterResources {
    MyTimelineClass myTimelineClass;
    Logger logger= LoggerFactory.getLogger(TwitterResources.class);

    public TwitterResources() {
    myTimelineClass = new MyTimelineClass();
    }

    public TwitterResources(MyTimelineClass myTimelineClass){
        this.myTimelineClass = myTimelineClass;
    }

    @GET
    @Path("/getTimeline")
    public Response getTimeline() {
        String str[] = myTimelineClass.myTimeline();
        logger.info("Retrieving Latest Tweets");
        return Response.ok(str).build();
    }

    @POST
    @Path("/postTweet")
    public Response postTweet(Request request) {
        String msg = request.getMsg();
        if (StringUtil.isEmpty(msg)) {
            logger.warn("Provide a valid tweet!");
            return Response.status(400, "Invalid!!,Please enter a valid tweet").build();
        } else {
            try {
                Status status = MyTweetClass.myTweet(msg);
                if (status.getText().equals(msg)) {
                    logger.info("Tweet posted successfully");
                    return Response.status(200, "Successfully Tweeted").build();
                } else {
                    logger.info("Unsuccessful!!, Tweet has not been posted");
                    return Response.status(400, "Request Incomplete!!").build();
                }
            } catch (TwitterException e) {
                logger.error("Exception has been occurred!!",e);
                return Response.status(500, "Request Incomplete!!").build();
            }
        }

    }
}