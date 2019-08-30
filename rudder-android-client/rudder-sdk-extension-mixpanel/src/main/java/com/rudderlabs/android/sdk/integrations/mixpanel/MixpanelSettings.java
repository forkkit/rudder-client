package com.rudderlabs.android.sdk.integrations.mixpanel;

import com.rudderlabs.android.sdk.core.RudderIntegrationSettings;

public class MixpanelSettings extends RudderIntegrationSettings {
    private String token;

    public MixpanelSettings(String token) {
        this.token = token;
    }

    public static MixpanelSettings getWithToken(String token) {
        return new MixpanelSettings(token);
    }

    @Override
    public String getToken() {
        return token;
    }
}
