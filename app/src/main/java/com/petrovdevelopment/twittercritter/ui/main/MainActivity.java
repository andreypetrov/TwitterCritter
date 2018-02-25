package com.petrovdevelopment.twittercritter.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.petrovdevelopment.twittercritter.R;
import com.petrovdevelopment.twittercritter.di.Injector;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    TwitterLoginButton loginButton;
    TextView infoView;

    MainContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        infoView = findViewById(R.id.info_view);
        initTwitterButton();
        //TODO inject twitter client in presenter
        Injector injector = (Injector) getApplication();
        presenter = new MainPresenter(this, injector);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    public void initTwitterButton() {
        loginButton = findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                presenter.onLoginSuccess();
            }

            @Override
            public void failure(TwitterException exception) {
                presenter.onLoginFailure(exception);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void showTweets(List<Tweet> tweets) {
        infoView.setText(tweets.size() + "\n" + tweets.toString());
    }

    @Override
    public void showError(String errorMessage) {
        infoView.setText(errorMessage);
    }
}
