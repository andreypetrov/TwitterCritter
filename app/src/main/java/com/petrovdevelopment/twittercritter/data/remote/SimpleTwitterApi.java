package com.petrovdevelopment.twittercritter.data.remote;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-22.
 */

public interface SimpleTwitterApi {

    public Observable<List<Tweet>> getTweets(int count);
}
