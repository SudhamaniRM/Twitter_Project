package com.Twitter_Project.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwitterResponse {
    private String message;
    private String createdAt;
    private User user;

    /**
     * TwitterResponse returns the details of user who posted tweet.Details are as followed:
     * @param message specifies tweet that has been posted.
     * @param twitterHandle specifies ScreenName of user who posted the tweet.
     * @param name specifies name of user who posted the tweet.
     * @param profileImageUrl specifies URL of profile image of user who posted the tweet.
     * @param createdAt specifies date and time tweet posted.
     */
    public TwitterResponse(String message, String twitterHandle, String name, String profileImageUrl, String createdAt) {
        this.message = message;
        this.user = new User(twitterHandle, name, profileImageUrl);
        this.createdAt = createdAt;
    }
}
