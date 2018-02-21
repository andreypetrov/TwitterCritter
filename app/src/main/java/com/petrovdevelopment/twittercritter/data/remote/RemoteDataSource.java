package com.petrovdevelopment.twittercritter.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.petrovdevelopment.twittercritter.data.TwitterDataSource;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public class RemoteDataSource implements TwitterDataSource{

    private static volatile RemoteDataSource instance;

    //TODO pass in okhttp client
    public RemoteDataSource getInstance() {
        if (instance == null) {
            synchronized (RemoteDataSource.class) {
                if (instance == null) {
                    instance = new RemoteDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public void getTweets(@NonNull GetTweetsCallback callback) {

    }

    @Override
    public void getTweets(@NonNull GetTweetsCallback callback, @NonNull Context context) {

    }
}
