package com.rudderlabs.android.sdk.integrations.adjust;

import com.rudderlabs.android.sdk.core.RudderIntegrationSettings;

import java.util.Map;

public class AdjustSettings extends RudderIntegrationSettings {
    private String token;
    private Map<String, String> eventTokenMap;

    public AdjustSettings(String token) {
        this.token = token;
    }

    public AdjustSettings(String token, Map<String, String> eventTokenMap) {
        this.token = token;
        this.eventTokenMap = eventTokenMap;
    }

    public static AdjustSettings getWithToken(String token) {
        return new AdjustSettings(token);
    }

    @Override
    public String getToken() {
        return token;
    }

    public String getToken(String eventName) {
        if (eventTokenMap.containsKey(eventName)) return eventTokenMap.get(eventName);
        return null;
    }

    public Map<String, String> getEventTokenMap() {
        return eventTokenMap;
    }
}
