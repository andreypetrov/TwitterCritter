package com.petrovdevelopment.twittercritter.data.local;

import android.content.Context;
import android.content.res.AssetManager;

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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import static org.mockito.ArgumentMatchers.any;
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
    @Mock
    AssetManager assetManager;
    @Mock
    InputStream inputStream;
    @Mock
    JsonParser jsonParser;
    @Captor
    ArgumentCaptor<Runnable> backgroundRunnableArgumentCaptor;
    @Captor
    ArgumentCaptor<Runnable> uiRunnableArgumentCaptor;
    @Captor
    ArgumentCaptor<Runnable> backgroundRunnableArgumentCaptor2;
    @Captor
    ArgumentCaptor<Runnable> uiRunnableArgumentCaptor2;


    AppExecutors appExecutors;

    @Before
    public void setup() {
        appExecutors = new AppExecutors(executor, executor, executor);
    }


    @Test
    public void getTweets() throws Exception {
        XmlDataSource xmlDataSource = XmlDataSource.getInstance(appExecutors, jsonParser);
        xmlDataSource.getTweets(getTweetsCallback, context);
        verify(appExecutors.getXmlIo()).execute(any(Runnable.class));
    }


    @Test
    public void verifyOnSuccessWasCalled() throws Exception {
        XmlDataSource xmlDataSource = XmlDataSource.getInstance(appExecutors, jsonParser);
        xmlDataSource.getTweets(getTweetsCallback, context);
        when(context.getAssets()).thenReturn(assetManager);
        when(assetManager.open(any(String.class))).thenReturn(inputStream);
        when(jsonParser.parseTweetsFromReader(isNotNull())).thenReturn(emptyDummyTweets());
        verify(appExecutors.getXmlIo()).execute(backgroundRunnableArgumentCaptor.capture());
        Runnable xmlRunnable = backgroundRunnableArgumentCaptor.getValue();
        xmlRunnable.run();
        verify(appExecutors.getMainThreadIo(), times(2)).execute(uiRunnableArgumentCaptor.capture());

        Runnable uiRunnable = uiRunnableArgumentCaptor.getValue();
        uiRunnable.run();
        verify(getTweetsCallback).onSuccess(any(List.class));
    }

    private List<Tweet> emptyDummyTweets() {
        return new ArrayList<>();
    }

    private List<Tweet> dummyTweetsFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("timeline.json");
        Reader inputStreamReader = null;
        List<Tweet> tweets = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            tweets = JsonParser.getInstance().parseTweetsFromReader(inputStreamReader);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return tweets;
    }

    @Test
    public void getTweets1() throws Exception {
        List<Tweet> dummyTweets = dummyTweetsFromFile();

        when(context.getAssets()).thenReturn(assetManager);
        when(assetManager.open(any(String.class))).thenReturn(inputStream);
        when(jsonParser.parseTweetsFromReader(any(Reader.class))).thenReturn(dummyTweets);
        XmlDataSource xmlDataSource = XmlDataSource.getInstance(appExecutors, jsonParser);
        xmlDataSource.getTweets(getTweetsCallback, context);
        verify(appExecutors.getXmlIo()).execute(backgroundRunnableArgumentCaptor2.capture());
        backgroundRunnableArgumentCaptor2.getValue().run();
        verify(appExecutors.getMainThreadIo(), times(2)).execute(uiRunnableArgumentCaptor2.capture());
        uiRunnableArgumentCaptor2.getValue().run();
        verify(getTweetsCallback).onSuccess(dummyTweets);
    }
}