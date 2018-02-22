package com.petrovdevelopment.twittercritter.ui.main;

import com.petrovdevelopment.twittercritter.data.remote.SimpleTwitterApi;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    SimpleTwitterApi simpleTwitterApi;

    public MainPresenter(MainContract.View view, SimpleTwitterApi simpleTwitterApi) {
        this.view = view;
        this.simpleTwitterApi = simpleTwitterApi;
    }

    @Override
    public void start() {
        //do nothing
    }


    @Override
    public void onLoginSuccess() {
        //TODO later move elsewhere
        Call<List<Tweet>> call = simpleTwitterApi.getSimpleStatusesService().homeTimeline(2);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.body() != null) {
                    view.showTweets(response.body());
                } else {
                    view.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });

    }

    @Override
    public void onLoginFailure(TwitterException e) {
        view.showError(e.getMessage());
    }
}
