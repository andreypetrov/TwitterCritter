package com.petrovdevelopment.twittercritter.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.petrovdevelopment.twittercritter.R;
import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.di.Injector;
import com.petrovdevelopment.twittercritter.model.Tweet;
import com.petrovdevelopment.twittercritter.model.TwitterError;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TwitterLoginButton loginButton;
    TextView infoView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        infoView = findViewById(R.id.info_view);
        initTwitterButton();
        testDataSource();

    }


    public void initTwitterButton() {
        loginButton = findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                infoView.setText(result.toString());
            }

            @Override
            public void failure(TwitterException exception) {
                infoView.setText(exception.getMessage());
            }
        });
    }

    //TODO move to test case class
    public void testDataSource() {
        Injector injector = (Injector) getApplication();
        TwitterDataSource twitterDataSource = injector.provideTwitterDataSource();
        twitterDataSource.getTweets(new TwitterDataSource.GetTweetsCallback() {
            @Override
            public void onSuccess(List<Tweet> tweets) {
                Log.i(this.getClass().getSimpleName(), tweets.toString());
            }

            @Override
            public void onFailure(TwitterError error) {
                Log.i(this.getClass().getSimpleName(), error.getMessage());
            }
        }, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
