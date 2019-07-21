package com.example.analyticslibrary.positive;

import com.example.analyticslibrary.BaseTestCase;
import com.rudderlabs.android.library.models.RudderEvent;
import com.rudderlabs.android.library.models.RudderEventBuilder;
import com.rudderlabs.android.library.models.porperties.PagePropertyBuilder;
import com.rudderlabs.android.library.models.porperties.ScreenPropertyBuilder;
import com.rudderlabs.android.library.models.porperties.TrackPropertyBuilder;

import org.junit.Test;


public class SimpleTestCases extends BaseTestCase {
    @Test
    public void testSimpleTrackEvent() throws InterruptedException {
        // track event
        RudderEvent pageViewEvent = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent("Test Track")
                .setPropertyBuilder(new TrackPropertyBuilder()
                        .setCategory("Test Category")
                        .setLabel("Test Label")
                        .setValue("Test Value"))
                .build();
        rudderClient.track(pageViewEvent);
        rudderClient.flush();
        Thread.sleep(2000);
    }

    @Test
    public void testSimplePageViewEvent() throws InterruptedException {
        // page view event
        RudderEvent pageViewEvent = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setPropertyBuilder(new PagePropertyBuilder()
                        .setUrl("http://jsonviewer.stack.hu")
                        .setKeywords("Test")
                        .setPath("http://jsonviewer.stack.hu")
                        .setReferrer("Test Event")
                        .setTitle("Test Title")
                        .setSearch("Test"))
                .build();
        rudderClient.page(pageViewEvent);
        rudderClient.flush();
        Thread.sleep(2000);
    }

    @Test
    public void testSimpleScreenViewEvent() throws InterruptedException {
        // screen view event
        RudderEvent screenViewEvent = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setPropertyBuilder(new ScreenPropertyBuilder()
                        .setScreenName("Test Screen"))
                .build();
        rudderClient.screen(screenViewEvent);
        rudderClient.flush();
        Thread.sleep(2000);
    }
}