package com.petrovdevelopment.twittercritter.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.model.TwitterError;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public class RemoteDataSource implements TwitterDataSource{
    private SimpleTwitterApi simpleTwitterApi;

    private static volatile RemoteDataSource instance;

    public RemoteDataSource(SimpleTwitterApi simpleTwitterApi) {
        this.simpleTwitterApi = simpleTwitterApi;
    }

    //TODO pass in okhttp client
    public RemoteDataSource getInstance(SimpleTwitterApi simpleTwitterApi) {
        if (instance == null) {
            synchronized (RemoteDataSource.class) {
                if (instance == null) {
                    instance = new RemoteDataSource(simpleTwitterApi);
                }
            }
        }
        return instance;
    }

    @Override
    public void getTweets(@NonNull GetTweetsCallback callback) {
        Call<List<Tweet>> call = simpleTwitterApi.getSimpleStatusesService().homeTimeline(2);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                //FIXME switch to using the official tweet model in the callback contract
                //callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
                callback.onFailure(new TwitterError(t, t.getMessage()));
            }
        });
    }

    @Override
    public void getTweets(@NonNull GetTweetsCallback callback, @NonNull Context context) {

    }
}
