package com.rudderlabs.android.sdk.integrations.fbappevent;

import com.rudderlabs.android.sdk.core.RudderIntegrationSettings;

public class FbAppEventSettings extends RudderIntegrationSettings {
    private String appID;

    public FbAppEventSettings(String appID) {
        this.appID = appID;
    }

    public static FbAppEventSettings getWithToken(String appID) {
        return new FbAppEventSettings(appID);
    }

    @Override
    public String getToken() {
        return appID;
    }
}
