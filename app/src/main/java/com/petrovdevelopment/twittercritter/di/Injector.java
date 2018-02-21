package com.petrovdevelopment.twittercritter.di;

import com.petrovdevelopment.twittercritter.data.TwitterDataSource;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public interface Injector {

    TwitterDataSource provideTwitterDataSource();

}
