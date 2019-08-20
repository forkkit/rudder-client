package com.rudderlabs.android.sdk;

import android.app.Application;
import com.rudderlabs.android.sdk.core.RudderClient;
import com.rudderlabs.android.sdk.core.RudderConfigBuilder;
import com.rudderlabs.android.sdk.core.RudderException;

public class MainApplication extends Application {
    private static MainApplication instance;
    private static RudderClient rudderClient;
    private static String SAMPLE_WRITE_KEY = "test-write-key";
    private static String END_POINT_URL = "http://cccd5697.ngrok.io";

    public RudderClient getRudderClient() {
        return rudderClient;
    }

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        try {
            rudderClient = RudderClient.getInstance(this, SAMPLE_WRITE_KEY, new RudderConfigBuilder().withEndPointUri(END_POINT_URL).withDebug(true));
        } catch (RudderException e) {
            e.printStackTrace();
        }
    }
}
