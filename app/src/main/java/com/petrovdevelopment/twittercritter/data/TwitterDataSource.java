package com.petrovdevelopment.twittercritter.data;

import android.content.Context;
import android.support.annotation.NonNull;


import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public interface TwitterDataSource {

    public Observable<List<Tweet>> getTweets();
    //needed for local retrieval
    public Observable<List<Tweet>> getTweets(@NonNull Context context);

}
