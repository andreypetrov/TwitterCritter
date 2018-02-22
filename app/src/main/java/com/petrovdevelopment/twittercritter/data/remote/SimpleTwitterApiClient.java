package com.petrovdevelopment.twittercritter.data.remote;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

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
}
