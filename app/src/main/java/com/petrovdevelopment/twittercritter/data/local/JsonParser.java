package com.petrovdevelopment.twittercritter.data.local;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitter.sdk.android.core.models.Tweet;

import java.io.Reader;
import java.util.List;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-20.
 */

public class JsonParser {
    Gson gson = new Gson();
    private static JsonParser instance;

    public static JsonParser getInstance() {
        if (instance == null) {
            synchronized (JsonParser.class) {
                if (instance == null) {
                    instance = new JsonParser();
                }
            }
        }
        return instance;
    }

    private JsonParser() {
    }

    public List<Tweet> parseTweetsFromReader(Reader reader) {
        List<Tweet> tweets = gson.fromJson(reader, new TypeToken<List<Tweet>>(){}.getType());
        return tweets;
    }
}
