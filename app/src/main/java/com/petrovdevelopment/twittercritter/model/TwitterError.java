package com.petrovdevelopment.twittercritter.model;

/**
 * Created by Andrey on 2018-02-19.
 */

public class TwitterError {
    private String message;
    private Throwable error;

    public TwitterError(Throwable error, String message) {
        this.error = error;
        this.message = message;
    }

    public Throwable getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
