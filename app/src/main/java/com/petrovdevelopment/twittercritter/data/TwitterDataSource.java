package com.petrovdevelopment.twittercritter.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.petrovdevelopment.twittercritter.model.Tweet;
import com.petrovdevelopment.twittercritter.model.TwitterError;

import java.util.List;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public interface TwitterDataSource {
    interface GetTweetsCallback {
        void onSuccess(List<Tweet> tweets);
        void onFailure(TwitterError error);
    }


    public void getTweets(@NonNull GetTweetsCallback  callback);
    //needed for local retrieval
    public void getTweets(@NonNull GetTweetsCallback  callback, @NonNull Context context);

}
