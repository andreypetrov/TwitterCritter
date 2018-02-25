package com.petrovdevelopment.twittercritter;

import android.app.Application;

import com.google.gson.Gson;
import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.data.local.JsonParser;
import com.petrovdevelopment.twittercritter.data.local.XmlDataSource;
import com.petrovdevelopment.twittercritter.data.remote.SimpleTwitterApi;
import com.petrovdevelopment.twittercritter.data.remote.SimpleTwitterApiClient;
import com.petrovdevelopment.twittercritter.di.Injector;
import com.petrovdevelopment.twittercritter.utils.AppExecutors;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public class MainApplication extends Application implements Injector{

    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
    }

    @Override
    public TwitterDataSource provideTwitterDataSource() {
        return XmlDataSource.getInstance(JsonParser.getInstance());
    }

    @Override
    public SimpleTwitterApi provideTwitterApiClient() {
        return new SimpleTwitterApiClient(TwitterCore.getInstance().getSessionManager().getActiveSession());
    }
}
