package com.petrovdevelopment.twittercritter.di;

import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.data.remote.SimpleTwitterApi;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public interface Injector {

    TwitterDataSource provideTwitterDataSource();
    SimpleTwitterApi provideTwitterApiClient();
}
