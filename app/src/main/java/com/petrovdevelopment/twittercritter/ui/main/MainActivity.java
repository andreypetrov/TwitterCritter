package com.petrovdevelopment.twittercritter.ui.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.petrovdevelopment.twittercritter.R;
import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.di.Injector;
import com.petrovdevelopment.twittercritter.model.Tweet;
import com.petrovdevelopment.twittercritter.model.TwitterError;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

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


}
