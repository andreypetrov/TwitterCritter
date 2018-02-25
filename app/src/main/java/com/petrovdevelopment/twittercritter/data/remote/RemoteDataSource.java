package com.petrovdevelopment.twittercritter.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.model.TwitterError;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
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
    public Observable<List<Tweet>> getTweets() {
        return simpleTwitterApi.getTweets(3)
        .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<Tweet>> getTweets(@NonNull Context context) {
        throw new UnsupportedOperationException("context is not required for networking data source. Instead use getTweets() without arguments");
    }
}
