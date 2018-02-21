package com.petrovdevelopment.twittercritter.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Coding is fun!
 * Created by Andrey on 2018-02-19.
 */

public class AppExecutors {
    private static final int THREAD_COUNT = 3;
    private static volatile AppExecutors instance;

    private Executor mainThreadIo;
    private Executor xmlIo;
    private Executor networkIo;

    public static AppExecutors getInstance() {
        if (instance == null) {
            synchronized (AppExecutors.class) {
                if (instance == null) {
                    instance = new AppExecutors();
                }
            }
        }
        return instance;
    }

    public AppExecutors() {
        this.mainThreadIo = new MainThreadExecutor();
        this.xmlIo = Executors.newSingleThreadExecutor();
        this.networkIo = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    @VisibleForTesting
    public AppExecutors(Executor xmlIo, Executor networkIo, Executor mainThreadIo) {
        this.xmlIo = xmlIo;
        this.networkIo = networkIo;
        this.mainThreadIo = mainThreadIo;
    }


    private static class MainThreadExecutor implements Executor {
        Handler mainThreadHandler;

        public MainThreadExecutor() {
            mainThreadHandler = new Handler(Looper.getMainLooper());
        }
        @Override
        public void execute(@NonNull Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }


    public Executor getMainThreadIo() {
        return mainThreadIo;
    }

    public Executor getXmlIo() {
        return xmlIo;
    }

    public Executor getNetworkIo() {
        return networkIo;
    }
}
