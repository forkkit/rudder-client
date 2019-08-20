package com.rudderlabs.android.sdk.core.event;

import com.google.gson.annotations.SerializedName;
import com.rudderlabs.android.sdk.core.RudderException;

import java.util.ArrayList;

public class RudderElement {
    @SerializedName("rl_message")
    private RudderMessage message;

    RudderElement() {
        message = new RudderMessage();
    }

    public void setType(String type) {
        message.setType(type);
    }

    public void setProperty(RudderProperty property) {
        message.setProperty(property);
    }

    public void setProperty(RudderPropertyBuilder builder) throws RudderException {
        setProperty(builder.build());
    }

    public void setIntegrations(ArrayList<String> integrations) {
        message.setIntegrations(integrations);
    }

    public void setUserId(String userId) {
        message.setUserId(userId);
    }

    public void setEventName(String eventName) {
        message.setEventName(eventName);
    }

    public void identifyWithTraits(RudderTraits traits) {
        message.updateTraits(traits);
    }
}