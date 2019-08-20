package com.rudderlabs.android.sdk.core.event;

import java.util.HashMap;
import java.util.Map;

public class RudderProperty {
    private Map<String, Object> map = new HashMap<>();

    public Object getProperty(String key) {
        return map.containsKey(key) ? map.get(key) : null;
    }

    public void setProperty(String key, Object value) {
        map.put(key, value);
    }

    public boolean hasProperty(String key) {
        return map.containsKey(key);
    }

    Map<String, Object> getMap() {
        return map;
    }

    public void setProperty(Map<String, Object> map) {
        this.map.putAll(map);
    }
}
