package com.petrovdevelopment.twittercritter.data.remote;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-22.
 */

public class SimpleTwitterApiClient extends TwitterApiClient implements SimpleTwitterApi {

    public SimpleTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public SimpleStatusesService getSimpleStatusesService() {
        return getService(SimpleStatusesService.class);
    }

    @Override
    public Observable<List<Tweet>> getTweets(int count) {
        return Observable.create(emitter -> {
                    Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
                        @Override
                        public void success(Result<List<Tweet>> result) {
                            emitter.onNext(result.data);
                            emitter.onComplete();
                        }

                        @Override
                        public void failure(TwitterException exception) {
                            emitter.onError(exception);
                        }
                    };
                    getSimpleStatusesService().homeTimeline(count).enqueue(callback);
                }
        );
    }
}
