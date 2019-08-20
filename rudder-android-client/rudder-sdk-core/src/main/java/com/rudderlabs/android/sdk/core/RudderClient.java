package com.rudderlabs.android.sdk.core;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.rudderlabs.android.sdk.core.event.*;

public class RudderClient {
    private static RudderClient instance;
    private static EventRepository repository;

    private RudderClient() {
        // prevent constructor initialization
    }

    public static RudderClient getInstance(Context context, String writeKey) throws RudderException {
        return getInstance(context, writeKey, RudderConfig.getDefaultConfig());
    }

    public static RudderClient getInstance(Context context, String writeKey, RudderConfigBuilder builder) throws RudderException {
        return getInstance(context, writeKey, builder.build());
    }

    public static RudderClient getInstance(Context context, String writeKey, RudderConfig config) throws RudderException {
        if (instance == null) {
            if (context == null) {
                throw new RudderException("context can not be null");
            }
            if (TextUtils.isEmpty(writeKey)) {
                throw new RudderException("WriteKey can not be null or empty");
            }
            if (config == null) {
                throw new RudderException("config can not be null");
            }
            instance = new RudderClient();
            Application application = (Application) context.getApplicationContext();
            RudderElementCache.initiate(application);
            repository = new EventRepository(application, writeKey, config);
        }
        return instance;
    }

    public void track(RudderElement event) {
        event.setType(MessageType.TRACK);
        repository.dump(event);
    }

    public void track(RudderElementBuilder builder) {
        track(builder.build());
    }

    public void screen(RudderElement event) {
        event.setType(MessageType.SCREEN);
        repository.dump(event);
    }

    public void screen(RudderElementBuilder builder) {
        screen(builder.build());
    }

    public void page(RudderElement event) {
        event.setType(MessageType.PAGE);
        repository.dump(event);
    }

    public void page(RudderElementBuilder builder) {
        page(builder.build());
    }

    public void flush() {
        repository.flushEvents();
    }

    public void identify(RudderTraits traits) {
        RudderElement event = new RudderElementBuilder().setEventName(MessageType.IDENTIFY).setUserId(traits.getId()).build();
        event.identifyWithTraits(traits);
        event.setType(MessageType.IDENTIFY);
    }
}
