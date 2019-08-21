package com.rudderlabs.android.sdk.core;

import android.app.Application;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EventRepository {
    private RudderConfig config;
    private ArrayList<RudderElement> events = new ArrayList<>();
    private ArrayList<RudderElement> eventBuffer = new ArrayList<>();
    private static String writeKey;
    private DBPersistentManager dbManager;
    private final ExecutorService batchExecutor = Executors.newSingleThreadExecutor();
    private final ExecutorService dumpExecutor = Executors.newSingleThreadExecutor();

    EventRepository(Application application, String key, RudderConfig config) {
        this.config = config;
        RudderElementCache.initiate(application);
        this.dbManager = new DBPersistentManager(application);
        writeKey = key;
    }

    void dump(RudderElement event) {
        eventBuffer.add(event);
        processEventBufferAsync();
    }

    private void processEventBufferAsync() {
        RudderElement element = eventBuffer.get(0);
        Runnable elementJob = createJobFromElement(element);
        dumpExecutor.submit(elementJob);
    }

    private Runnable createJobFromElement(final RudderElement element) {
        return new Runnable() {
            @Override
            public void run() {
                for (RudderIntegrationFactory integration : config.getIntegrations())
                    element.addIntegrationProps(integration.key(), integration.enabled(), integration.getDestinationProps());
                events.add(element);

                if (events.size() == config.getFlushQueueSize()) {
                    flushEvents();
                }
            }
        };
    }

    void flushEvents() {
        String payload = new Gson().toJson(new RudderPayload(events, writeKey));
        RudderLogger.logInfo("PAYLOAD: " + payload);

        processPayloadAsync(payload);
    }

    private void processPayloadAsync(final String payload) {
        batchExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    dbManager.saveBatch(payload);
                    NetworkServiceManager.sendEventToServer(dbManager, config.getEndPointUri());
                } catch (IOException e) {
                    RudderLogger.logError(e.getCause());
                }
            }
        });
    }
}
