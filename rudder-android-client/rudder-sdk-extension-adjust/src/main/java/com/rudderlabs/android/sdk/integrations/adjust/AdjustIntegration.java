package com.rudderlabs.android.sdk.integrations.adjust;

import android.app.Application;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustInstance;
import com.rudderlabs.android.sdk.core.*;

import java.util.Map;

public class AdjustIntegration extends RudderIntegrationFactory<AdjustInstance> {
    public AdjustIntegration(String appToken) {
        this(AdjustSettings.getWithToken(appToken));
    }

    public AdjustIntegration(RudderIntegrationSettings settings) {
        super(settings);
    }

    @Override
    public void init(Application application, RudderClient client, RudderConfig config) {
        AdjustConfig adjustConfig = new AdjustConfig(application, getSettings().getToken(), config.isDebug() ? AdjustConfig.ENVIRONMENT_SANDBOX : AdjustConfig.ENVIRONMENT_PRODUCTION);
        Adjust.onCreate(adjustConfig);
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
        return "AD";
    }

    @Override
    public boolean enabled() {
        return true;
    }

    @Override
    public AdjustInstance getInstance() {
        return Adjust.getDefaultInstance();
    }

    @Override
    public Map<String, Object> getDestinationProps() {
        return null;
    }
}
