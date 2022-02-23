package com.Twitter_Project.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendResponse {
    private String message;

    /**
     * Used to send response on tweet posted.
     * @param message is a response message.
     */
    public SendResponse(String message) {
        this.message = message;
    }
}
