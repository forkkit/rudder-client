package com.rudderlabs.android.sdk.core.event;

import com.google.gson.annotations.SerializedName;
import com.rudderlabs.android.sdk.core.util.Utils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

class RudderMessage {
    @SerializedName("rl_channel")
    private String channel = "android-sdk";
    @SerializedName("rl_context")
    private RudderContext context;
    @SerializedName("rl_type")
    private String type;
    @SerializedName("rl_action")
    private String action;
    @SerializedName("rl_message_id")
    private String messageId = String.format(Locale.US, "%d-%s", System.currentTimeMillis(), UUID.randomUUID().toString());
    @SerializedName("rl_timestamp")
    private String timestamp = Utils.getTimeStamp();
    @SerializedName("rl_anonymous_id")
    private String anonymousId;
    @SerializedName("rl_user_id")
    private String userId;
    @SerializedName("rl_event")
    private String event;
    @SerializedName("rl_properties")
    private Map<String, Object> properties;
    @SerializedName("rl_user_properties")
    private Map<String, Object> userProperties;
    @SerializedName("rl_integrations")
    private ArrayList<String> integrations = new ArrayList<>();

    RudderMessage() {
        context = RudderElementCache.getCachedContext();
    }

    void setProperty(RudderProperty property) {
        if (property != null) this.properties = property.getMap();
    }

    void setUserProperties(RudderUserProperty userProperty) {
        this.userProperties = userProperty.getMap();
    }

    void setType(String type) {
        this.type = type;
    }

    void setIntegrations(ArrayList<String> integrations) {
        this.integrations.addAll(integrations);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEventName(String eventName) {
        this.event = eventName;
    }

    void updateTraits(RudderTraits traits) {
        this.context.updateTraits(traits);
    }
}
