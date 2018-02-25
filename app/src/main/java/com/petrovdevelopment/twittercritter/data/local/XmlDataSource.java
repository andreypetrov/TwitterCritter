package com.petrovdevelopment.twittercritter.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.model.TwitterError;
import com.petrovdevelopment.twittercritter.utils.AppExecutors;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public class XmlDataSource implements TwitterDataSource{
    private static final String LOCAL_FILE_NAME = "timeline.json";
    private static volatile XmlDataSource instance;

    private JsonParser jsonParser;


    public XmlDataSource(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public static XmlDataSource getInstance(@NonNull JsonParser jsonParser) {
        if (instance == null) {
            synchronized (XmlDataSource.class) {
                if (instance == null) {
                    instance = new XmlDataSource(jsonParser);
                }
            }
        }
        return instance;
    }

    @Override
    public Observable<List<Tweet>> getTweets() {
        throw new UnsupportedOperationException("for local xml you need the context passed in");
    }

    @Override
    public Observable<List<Tweet>> getTweets(Context context) {
        return Observable.fromCallable(() -> context.getAssets().open(LOCAL_FILE_NAME))
                .subscribeOn(Schedulers.io())
                .map((inputStream) -> new InputStreamReader(inputStream, "UTF-8"))
                .map(reader -> jsonParser.parseTweetsFromReader(reader));
    }
}
