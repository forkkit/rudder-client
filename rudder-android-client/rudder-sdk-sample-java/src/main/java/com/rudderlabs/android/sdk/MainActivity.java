package com.rudderlabs.android.sdk;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.rudderlabs.android.sdk.core.RudderException;
import com.rudderlabs.android.sdk.core.event.*;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            RudderElement trackEvent = new RudderElementBuilder().setEventName("Test Track")
                    .setProperty(new TrackPropertyBuilder().setCategory("Test Category")).build();
            MainApplication.getInstance().getRudderClient().track(trackEvent);

            RudderElement pageEvent = new RudderElementBuilder().setEventName("Test Track")
                    .setProperty(new PagePropertyBuilder().setUrl("Test Url")).build();
            MainApplication.getInstance().getRudderClient().page(pageEvent);

            RudderElement screenEvent = new RudderElementBuilder().setEventName("Test Track")
                    .setProperty(new ScreenPropertyBuilder().setScreenName("Test Name")).build();
            MainApplication.getInstance().getRudderClient().screen(screenEvent);

            MainApplication.getInstance().getRudderClient().flush();
        } catch (RudderException e) {
            e.printStackTrace();
        }
    }
}
