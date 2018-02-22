package com.petrovdevelopment.twittercritter.data.remote;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-22.
 */

public interface SimpleStatusesService {

    @GET("/1.1/statuses/home_timeline.json?" +
            "tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<List<Tweet>> homeTimeline(@Query("count") Integer count);
}
