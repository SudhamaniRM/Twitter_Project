package com.Twitter_Project.resources;

import org.springframework.stereotype.Service;

@Service
public class Request {
    String msg;

    public Request(String msg) {
        this.msg = msg;
    }

    public Request() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
