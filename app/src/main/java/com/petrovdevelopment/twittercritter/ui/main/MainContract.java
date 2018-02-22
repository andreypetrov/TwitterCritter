package com.petrovdevelopment.twittercritter.ui.main;

import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public interface MainContract {

    interface View {
        void showTweets(List<Tweet> tweets);
        void showError(String errorMessage);
    }

    interface Presenter {
        void start();
        void onLoginSuccess();
        void onLoginFailure(TwitterException e);

    }
}
