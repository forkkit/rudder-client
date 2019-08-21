package com.rudderlabs.android.sdk.core;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RudderClient {
    private static RudderClient instance;
    private static EventRepository repository;
    private static Application application;
    private static RudderConfig config;

    private static ExecutorService integrationExecutor = Executors.newSingleThreadExecutor();

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
            application = (Application) context.getApplicationContext();
            repository = new EventRepository(application, writeKey, config);
            RudderClient.config = config;

            initiateIntegrations(config);
        }
        return instance;
    }

    private static void initiateIntegrations(final RudderConfig config) {
        integrationExecutor.submit(new Runnable() {
            @Override
            public void run() {
                for (RudderIntegrationFactory integration : config.getIntegrations()) {
                    integration.init(application, instance, config);
                }
            }
        });
    }

    public void track(final RudderElement event, boolean withIntegration) {
        event.setType(MessageType.TRACK);
        repository.dump(event);
        if (withIntegration) {
            integrationExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    for (RudderIntegrationFactory integration : config.getIntegrations()) {
                        integration.track(event);
                    }
                }
            });
        }
    }

    public void track(RudderElement event) {
        track(event, true);
    }

    public void track(RudderElementBuilder builder) {
        track(builder.build());
    }

    public void screen(final RudderElement event, boolean withIntegration) {
        event.setType(MessageType.SCREEN);
        repository.dump(event);
        if (withIntegration) {
            integrationExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    for (RudderIntegrationFactory integration : config.getIntegrations()) {
                        integration.screen(event);
                    }
                }
            });
        }
    }

    public void screen(RudderElement event) {
        screen(event, true);
    }

    public void screen(RudderElementBuilder builder) {
        screen(builder.build());
    }

    public void page(final RudderElement event, boolean withIntegration) {
        event.setType(MessageType.PAGE);
        repository.dump(event);
        if (withIntegration) {
            integrationExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    for (RudderIntegrationFactory integration : config.getIntegrations()) {
                        integration.page(event);
                    }
                }
            });
        }
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

    public void identify(final RudderElement event, boolean withIntegration) {
        repository.dump(event);
        if (withIntegration) {
            integrationExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    for (RudderIntegrationFactory integration : config.getIntegrations()) {
                        integration.identify(event);
                    }
                }
            });
        }
    }

    public void identify(RudderTraits traits) {
        RudderElement event = new RudderElementBuilder().setEventName(MessageType.IDENTIFY).setUserId(traits.getId()).build();
        event.identifyWithTraits(traits);
        event.setType(MessageType.IDENTIFY);
        identify(event, true);
    }

    public void identify(RudderTraitsBuilder builder) {
        identify(builder.build());
    }
}
