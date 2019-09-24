package com.rudderlabs.android.sdk.ecomm;

import android.content.Context;

import com.rudderlabs.android.sdk.core.RudderClient;
import com.rudderlabs.android.sdk.core.RudderConfig;
import com.rudderlabs.android.sdk.core.RudderConfigBuilder;
import com.rudderlabs.android.sdk.core.RudderElementBuilder;
import com.rudderlabs.android.sdk.core.RudderException;
import com.rudderlabs.android.sdk.core.RudderUserProperty;

public class RudderECommerceClient {
    private static RudderECommerceClient instance;
    private static RudderClient client;

    // stop constructor initialization
    private RudderECommerceClient() {

    }

    public static RudderECommerceClient getInstance(Context context, String writeKey) throws RudderException {
        return getInstance(context, writeKey, new RudderConfigBuilder().build());
    }

    public static RudderECommerceClient getInstance(Context context, String writeKey, RudderConfig config) throws RudderException {
        if (instance == null) {
            instance = new RudderECommerceClient();
            client = RudderClient.getInstance(context, writeKey, config);
        }
        return instance;
    }

    public void track(ECommercePropertyBuilder builder) throws RudderException {
        track(builder, null, null);
    }

    public void track(ECommercePropertyBuilder builder, RudderUserProperty userProperty) throws RudderException {
        track(builder, userProperty, null);
    }

    public void track(ECommercePropertyBuilder builder, String userId) throws RudderException {
        track(builder, null, userId);
    }

    public void track(ECommercePropertyBuilder builder, RudderUserProperty userProperty, String userId) throws RudderException {
        RudderElementBuilder elementBuilder = new RudderElementBuilder()
                .setEventName(builder.event())
                .setProperty(builder.build());
        if (userProperty != null) elementBuilder.setUserProperty(userProperty);
        if (userId != null) elementBuilder.setUserId(userId);
        client.track(elementBuilder);
    }

    public RudderClient getClient() {
        return client;
    }
}
