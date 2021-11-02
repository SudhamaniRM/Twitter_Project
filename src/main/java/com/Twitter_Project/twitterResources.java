package com.Twitter_Project;

import org.eclipse.jetty.util.StringUtil;
import twitter4j.TwitterException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/1.0/twitter")
public class twitterResources {
    @GET
    @Path("/getTimeline")
    public String[] getTimeline(){
        String str[] = new String[0];
        try {
            str = myTimeline.MyTimeline();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return str;
    }

    @POST
    @Path("/postTweet")
    public Response postTweet(Request request) {
        String msg=request.getMsg();
        if (StringUtil.isEmpty(msg)) {
            return Response.status(400, "Invalid!!,Please enter a valid tweet").build();
        } else {
            try {
                myTweet.MyTweet(msg);
            }
            catch (TwitterException e) {
                return Response.status(500, "Request Incomplete!!").build();
            }
        }
        return Response.status(200, "Successfully Tweeted!!").build();
    }
}