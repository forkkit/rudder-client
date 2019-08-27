package com.example.rudder.sdk.extension.fbappevent;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class TestFbSdk extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.setApplicationId("");
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

}
