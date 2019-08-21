package com.rudderlabs.android.sdk.core;

import android.app.Application;

import java.util.Map;

public abstract class RudderIntegrationFactory<T> {
    private RudderIntegrationSettings settings;

    private RudderIntegrationFactory() {
    }

    public RudderIntegrationFactory(RudderIntegrationSettings settings) {
        this.settings = settings;
    }

    public abstract void init(Application application, RudderClient client, RudderConfig config);

    public abstract void identify(RudderElement element);

    public abstract void page(RudderElement element);

    public abstract void screen(RudderElement element);

    public abstract void track(RudderElement element);

    public abstract String key();

    public abstract boolean enabled();

    public abstract T getInstance();

    public abstract Map<String, Object> getDestinationProps();

    protected RudderIntegrationSettings getSettings() {
        return settings;
    }
}