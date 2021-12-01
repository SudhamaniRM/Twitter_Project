package com.Twitter_Project.models;

public class SendResponse {
    private String message;

    public SendResponse(String message) {
        this.message = message;
    }

    public SendResponse() {
    }

    public String getMessage() {
        return message;
    }

    public String setMessage(String message) {
        this.message = message;
        return message;
    }


}
