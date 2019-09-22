package com.rudderlabs.android.sdk.integrations.adjust;

import android.os.Build;

import com.rudderlabs.android.sdk.core.RudderIntegrationSettings;

import java.util.HashMap;
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

    public static class Builder {
        private String token;

        public Builder withToken(String token) {
            this.token = token;
            return this;
        }

        private Map<String, String> eventTokenMap;

        public Builder withMap(Map<String, String> eventMap) {
            if (this.eventTokenMap == null) {
                this.eventTokenMap = new HashMap<>();
            }
            this.eventTokenMap.putAll(eventMap);
            return this;
        }

        public Builder withMapEntry(String eventName, String eventToken) {
            if (this.eventTokenMap == null) {
                this.eventTokenMap = new HashMap<>();
            }
            this.eventTokenMap.put(eventName, eventToken);
            return this;
        }

        public AdjustSettings build() {
            return new AdjustSettings(this.token, this.eventTokenMap);
        }
    }
}
