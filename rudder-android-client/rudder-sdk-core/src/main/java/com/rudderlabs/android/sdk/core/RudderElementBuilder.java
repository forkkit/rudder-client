package com.rudderlabs.android.sdk.core;

import java.util.Map;

public class RudderElementBuilder {
    private String eventName = null;

    public RudderElementBuilder setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    private String userId = null;

    public RudderElementBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    private RudderProperty property;

    public RudderElementBuilder setProperty(RudderProperty property) {
        this.property = property;
        return this;
    }

    public RudderElementBuilder setProperty(RudderPropertyBuilder builder) throws RudderException {
        this.property = builder.build();
        return this;
    }

    public RudderElementBuilder setProperty(Map<String, Object> map) {
        property = new RudderProperty();
        property.setProperty(map);
        return this;
    }

    public RudderElement build() {
        RudderElement event = new RudderElement();
        event.setUserId(userId);
        event.setEventName(eventName);
        event.setProperty(property);
        return event;
    }
}
