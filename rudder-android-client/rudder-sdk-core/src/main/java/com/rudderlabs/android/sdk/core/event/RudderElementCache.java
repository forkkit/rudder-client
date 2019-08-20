package com.rudderlabs.android.sdk.core.event;

import android.app.Application;

public class RudderElementCache {
    private static RudderContext cachedContext;

    private RudderElementCache() {
        // stop instantiating
    }

    public static void initiate(Application application) {
        if (cachedContext == null) {
            cachedContext = new RudderContext(application);
        }
    }

    static RudderContext getCachedContext() {
        return cachedContext;
    }
}

