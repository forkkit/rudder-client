package com.rudderlabs.android.sdk.integrations.fbappevent;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.rudderlabs.android.sdk.core.RudderClient;
import com.rudderlabs.android.sdk.core.RudderConfig;
import com.rudderlabs.android.sdk.core.RudderElement;
import com.rudderlabs.android.sdk.core.RudderIntegrationFactory;
import com.rudderlabs.android.sdk.core.RudderIntegrationSettings;

import java.util.Map;

public class FbAppEventIntegration extends RudderIntegrationFactory<FacebookSdk> {
    private RudderClient rudderClient;
    private Application application;

    public FbAppEventIntegration(String appID) {
        this(FbAppEventSettings.getWithToken(appID));
    }

    public FbAppEventIntegration(RudderIntegrationSettings settings) {
        super(settings);
    }

    @Override
    public void init(Application application, RudderClient client, RudderConfig config) {
        this.application = application;
        this.rudderClient = client;


        FacebookSdk.setApplicationId(this.getSettings().getToken());
        FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.fullyInitialize();
        // FacebookSdk.sdkInitialize(application);

    }

    @Override
    public void identify(RudderElement element) {

    }

    @Override
    public void page(RudderElement element) {

    }

    @Override
    public void screen(RudderElement element) {

    }

    @Override
    public void track(RudderElement element) {

    }

    @Override
    public String key() {
        return null;
    }

    @Override
    public boolean enabled() {
        return false;
    }

    @Override
    public FacebookSdk getInstance() {
        return null;
    }

    @Override
    public Map<String, Object> getDestinationProps(RudderElement element) {
        return null;
    }
}
