package com.example.analyticslibrary;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.rudderlabs.android.library.RudderClient;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class BaseTestCase {
    protected RudderClient rudderClient;

    @Before
    public void setup() throws InterruptedException {
        Context context = ApplicationProvider.getApplicationContext();
//        rudderClient = RudderClient.getInstance(context, 5, "http://54.172.64.144:8080/", false);
        rudderClient = RudderClient.getInstance(context, 5, "http://08cf8769.ngrok.io/", false);
        Thread.sleep(1000);
    }
}
