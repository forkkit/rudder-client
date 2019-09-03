package com.rudderlabs.android.sdk.integrations.mixpanel;

import org.json.JSONObject;

import java.util.Map;

class MixpanelEvent {
    private String eventType;
    private String eventName;

    private JSONObject eventProps;
    private boolean isRevenueEvent;
    private String revenue;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(String distinctId) {
        this.distinctId = distinctId;
    }

    public JSONObject getTrackChargeProperty() {
        return trackChargeProperty;
    }

    public void setTrackChargeProperty(JSONObject trackChargeProperty) {
        this.trackChargeProperty = trackChargeProperty;
    }

    private String distinctId;
    private JSONObject trackChargeProperty;

    public MixpanelEvent(String eventName) {
        this.eventName = eventName;
    }

    public MixpanelEvent(String eventType, String eventName, JSONObject eventProps) {
        this.eventType = eventType;
        this.eventName = eventName;
        this.eventProps = eventProps;
    }

    public String getEventType() {
        return eventType;
    }

    public boolean isRevenueEvent() {
        return isRevenueEvent;
    }

    public void setRevenueEvent(boolean revenueEvent) {
        isRevenueEvent = revenueEvent;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public JSONObject getEventProps() {
        return eventProps;
    }

    public void setEventProps(JSONObject eventProps) {
        this.eventProps = eventProps;
    }

    /*public void setEventProps(String key, Object value) {
        this.eventProps.put(key, value);
    }*/
}
