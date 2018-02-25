package com.petrovdevelopment.twittercritter.ui.main;

import com.petrovdevelopment.twittercritter.data.remote.SimpleTwitterApi;
import com.petrovdevelopment.twittercritter.di.Injector;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    private Injector injector;
    private SimpleTwitterApi simpleTwitterApi;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainPresenter(MainContract.View view, Injector injector) {
        this.view = view;
        this.injector = injector;
    }

    @Override
    public void start() {
        //do nothing
    }

    @Override
    public void stop() {
        compositeDisposable.clear();
    }


    @Override
    public void onLoginSuccess() {
        this.simpleTwitterApi = injector.provideTwitterApiClient(); //inject only after successful twitter login, because before that twitter session will be null
        compositeDisposable.add(
        simpleTwitterApi.getTweets(2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tweets -> view.showTweets(tweets),
                        e -> view.showError(e.getMessage())));



// Use the one below if you want to create a whole observer instead of using the short version above.
// this may be useful if we want to define a separate class implementing disposable observer and having more logic in it.
//                .subscribeWith(new DisposableObserver<List<Tweet>>() {
//            @Override
//            public void onNext(List<Tweet> tweets) {
//                view.showTweets(tweets);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                view.showError(e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        }));

    }

    @Override
    public void onLoginFailure(TwitterException e) {
        view.showError(e.getMessage());
    }
}
