package com.petrovdevelopment.twittercritter.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.model.Tweet;
import com.petrovdevelopment.twittercritter.model.TwitterError;
import com.petrovdevelopment.twittercritter.utils.AppExecutors;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public class XmlDataSource implements TwitterDataSource{
    private static final String LOCAL_FILE_NAME = "timeline.json";
    private static volatile XmlDataSource instance;

    private JsonParser jsonParser;
    private AppExecutors appExecutors;


    public XmlDataSource(@NonNull AppExecutors appExecutors, JsonParser jsonParser) {
        this.appExecutors = appExecutors;
        this.jsonParser = jsonParser;
    }

    public static XmlDataSource getInstance(@NonNull AppExecutors appExecutors, @NonNull JsonParser jsonParser) {
        if (instance == null) {
            synchronized (XmlDataSource.class) {
                if (instance == null) {
                    instance = new XmlDataSource(appExecutors, jsonParser);
                }
            }
        }
        return instance;
    }

    @Override
    public void getTweets(@NonNull GetTweetsCallback callback) {
        throw new UnsupportedOperationException("for local xml you need the context passed in");
    }




    @Override
    public void getTweets(final @NonNull GetTweetsCallback callback, Context context) {
        Runnable runnable = () -> {
            try {
                InputStream inputStream = context.getAssets().open(LOCAL_FILE_NAME);
                Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                List<Tweet> tweets = jsonParser.parseTweetsFromReader(inputStreamReader);
                appExecutors.getMainThreadIo().execute(() -> callback.onSuccess(tweets));
            } catch (IOException e) {
                appExecutors.getMainThreadIo().execute(() -> callback.onFailure(new TwitterError(e, e.getMessage())));
            }
        };
        appExecutors.getXmlIo().execute(runnable);
    }
}
