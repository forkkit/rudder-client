package com.rudderlabs.android.sdk.integrations.adjust;

import com.rudderlabs.android.sdk.core.RudderIntegrationSettings;

public class AdjustSettings extends RudderIntegrationSettings {
    private String token;

    public AdjustSettings(String token) {
        this.token = token;
    }

    public static AdjustSettings getWithToken(String token) {
        return new AdjustSettings(token);
    }

    @Override
    public String getToken() {
        return token;
    }
}
