package com.rudderlabs.android.sdk.core;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.rudderlabs.android.sdk.core.ecommerce.ECommercePropertyBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Primary class to be used in client
 * */
public class RudderClient {
    // singleton instance
    private static RudderClient instance;
    // repository instance
    private static EventRepository repository;
    // persisted application instance
    private static Application application;
    // persisted config object for the client
    private static RudderConfig config;
    // single thread executor for executing integration initialization
    private static ExecutorService integrationExecutor = Executors.newSingleThreadExecutor();

    /*
     * private constructor
     * */
    private RudderClient() {
        // prevent constructor initialization
    }

    /*
     * API for getting instance of RudderClient with context and writeKey (bare minimum
     * requirements)
     * */
    public static RudderClient getInstance(Context context, String writeKey) throws RudderException {
        return getInstance(context, writeKey, RudderConfig.getDefaultConfig());
    }

    /*
     * API for getting instance of RudderClient with config
     * */
    public static RudderClient getInstance(Context context, String writeKey,
                                           RudderConfigBuilder builder) throws RudderException {
        return getInstance(context, writeKey, builder.build());
    }

    /*
     * API for getting instance of RudderClient with config
     * */
    public static RudderClient getInstance(Context context, String writeKey, RudderConfig config)
            throws RudderException {
        // check if instance is already initiated
        if (instance == null) {
            // assert context is not null
            if (context == null) {
                throw new RudderException("context can not be null");
            }
            // assert writeKey is not null or empty
            if (TextUtils.isEmpty(writeKey)) {
                throw new RudderException("WriteKey can not be null or empty");
            }
            // assert config is not null
            if (config == null) {
                throw new RudderException("config can not be null");
            }

            // initiate RudderClient instance
            instance = new RudderClient();
            // get application context from provided context
            application = (Application) context.getApplicationContext();
            // initiate EventRepository class
            repository = new EventRepository(application, writeKey, config);
            // persist provided config object
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

    public void track(ECommercePropertyBuilder builder) {
        try {
            RudderElement element = new RudderElementBuilder()
                    .setEventName(builder.event())
                    .setProperty(builder.build())
                    .build();
            track(element, true);
        } catch (RudderException e) {
            RudderLogger.logError(e.getCause());
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
        RudderElement event = new RudderElementBuilder()
                .setEventName(MessageType.IDENTIFY)
                .setUserId(traits.getId())
                .build();
        event.identifyWithTraits(traits);
        event.setType(MessageType.IDENTIFY);
        identify(event, true);
    }

    public void identify(RudderTraitsBuilder builder) {
        identify(builder.build());
    }
}
