package com.rudderlabs.android.sdk.core;

import android.app.Application;
import com.google.gson.Gson;
import com.rudderlabs.android.sdk.core.event.RudderElement;
import com.rudderlabs.android.sdk.core.event.RudderPayload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EventRepository {
    private RudderConfig config;
    private ArrayList<RudderElement> events = new ArrayList<>();
    private static String writeKey;
    private DBPersistentManager dbManager;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    EventRepository(Application application, String key, RudderConfig config) {
        this.config = config;
        this.dbManager = new DBPersistentManager(application);
        writeKey = key;
    }

    void dump(RudderElement event) {
        event.setIntegrations(config.getIntegrations());
        events.add(event);

        if (events.size() == config.getFlushQueueSize()) {
            flushEvents();
            events.clear();
        }
    }

    void flushEvents() {
        String payload = new Gson().toJson(new RudderPayload(events, writeKey));
        RudderLogger.logInfo("PAYLOAD: " + payload);

        processPayload(payload);
    }

    private void processPayload(final String payload) {
        executor.submit(new Runnable() {
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
