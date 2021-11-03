package com.Twitter_Project;

import org.eclipse.jetty.util.StringUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class TwitterResources {
    Twitter twitter = TwitterFactory.getSingleton();

    @GET
    @Path("/getTimeline")
    public Response getTimeline() {
        String str[] = MyTimelineClass.myTimeline();
        return Response.ok(str).build();
    }

    @POST
    @Path("/postTweet")
    public Response postTweet(Request request) {
        String msg = request.getMsg();
        if (StringUtil.isEmpty(msg)) {
            return Response.status(400, "Invalid!!,Please enter a valid tweet").build();
        } else {
            try {
                Status status = twitter.updateStatus(msg);
                if (status.getText().equals(msg)) {
                    return Response.status(200, "Successfully Tweeted").build();
                } else {
                    return Response.status(400, "Request Incomplete!!").build();
                }
            } catch (TwitterException e) {
                return Response.status(500, "Request Incomplete!!").build();
            }
        }

    }
}