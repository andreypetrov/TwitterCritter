package com.petrovdevelopment.twittercritter.data.local;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.petrovdevelopment.twittercritter.data.TwitterDataSource;
import com.petrovdevelopment.twittercritter.model.Tweet;
import com.petrovdevelopment.twittercritter.utils.AppExecutors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-20.
 */
public class XmlDataSourceTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Executor executor;

    @Mock
    Context context;

    @Mock
    TwitterDataSource.GetTweetsCallback getTweetsCallback;

    @Captor
    ArgumentCaptor<Runnable> backgroundRunnableArgumentCaptor;
    @Captor
    ArgumentCaptor<Runnable> uiRunnableArgumentCaptor;

    AppExecutors appExecutors;

    @Before
    public void setup() {
        appExecutors = new AppExecutors(executor, executor, executor);
    }

    @Mock
    AssetManager assetManager;

    @Mock
    InputStream inputStream;

    @Mock
    JsonParser jsonParser;

    @Test
    public void verifyOnSuccessWasCalled() throws Exception {
        XmlDataSource xmlDataSource = XmlDataSource.getInstance(appExecutors, jsonParser);
        xmlDataSource.getTweets(getTweetsCallback, context);
        when(context.getAssets()).thenReturn(assetManager);
        when(assetManager.open(any(String.class))).thenReturn(inputStream);
        when(jsonParser.parseTweetsFromReader(isNotNull())).thenReturn(dummyTweets());
        verify(appExecutors.getXmlIo()).execute(backgroundRunnableArgumentCaptor.capture());
        Runnable xmlRunnable = backgroundRunnableArgumentCaptor.getValue();
        xmlRunnable.run();
        verify(appExecutors.getMainThreadIo(), times(2)).execute(uiRunnableArgumentCaptor.capture());

        Runnable uiRunnable = uiRunnableArgumentCaptor.getValue();
        uiRunnable.run();
        verify(getTweetsCallback).onSuccess(isNotNull());
    }

    private List<Tweet> dummyTweets() {
        return new ArrayList<>();
    }


    @Test
    public void getTweets() throws Exception {
        AppExecutors appExecutors = new AppExecutors(executor, executor, executor);
        XmlDataSource xmlDataSource = XmlDataSource.getInstance(appExecutors, jsonParser);
        xmlDataSource.getTweets(getTweetsCallback, context);
        verify(appExecutors.getXmlIo()).execute(any(Runnable.class));
    }

    @Test
    public void getTweets1() throws Exception {
    }


}