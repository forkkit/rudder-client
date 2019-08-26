package com.rudderlabs.android.sdk.integrations.adjust;

import android.app.Application;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.AdjustInstance;
import com.rudderlabs.android.sdk.core.RudderClient;
import com.rudderlabs.android.sdk.core.RudderConfig;
import com.rudderlabs.android.sdk.core.RudderElement;
import com.rudderlabs.android.sdk.core.RudderIntegrationFactory;
import com.rudderlabs.android.sdk.core.RudderIntegrationSettings;

import java.util.HashMap;
import java.util.Map;

public class AdjustIntegration extends RudderIntegrationFactory<AdjustInstance> {
    private AdjustSettings settings;
    private static final String ADJUST_TYPE = "type";

    public AdjustIntegration(String appToken) {
        this(AdjustSettings.getWithToken(appToken));
    }

    public AdjustIntegration(RudderIntegrationSettings settings) {
        super(settings);
        this.settings = (AdjustSettings) settings;
    }

    @Override
    public void init(Application application, RudderClient client, RudderConfig config) {
        AdjustConfig adjustConfig = new AdjustConfig(application, getSettings().getToken(), config.isDebug() ? AdjustConfig.ENVIRONMENT_SANDBOX : AdjustConfig.ENVIRONMENT_PRODUCTION);
        Adjust.onCreate(adjustConfig);
    }

    @Override
    public void identify(RudderElement element) {
        processRudderEvent(element);
    }

    @Override
    public void page(RudderElement element) {
        processRudderEvent(element);
    }

    @Override
    public void screen(RudderElement element) {
        processRudderEvent(element);
    }

    @Override
    public void track(RudderElement element) {
        processRudderEvent(element);
    }

    private void processRudderEvent(RudderElement element) {
        String token = settings.getToken(element.getEventName());
        if (token == null) return;
        AdjustEvent event = new AdjustEvent(token);
        event.addCallbackParameter(ADJUST_TYPE, element.getType());
        Map<String, Object> eventProperties = element.getProperties();
        if (eventProperties != null) {
            for (String key : eventProperties.keySet()) {
                event.addPartnerParameter(key, String.valueOf(eventProperties.get(key)));
            }

            if (eventProperties.containsKey("total") && eventProperties.containsKey("currency")) {
                event.setRevenue(
                        Double.parseDouble(String.valueOf(eventProperties.get("total"))),
                        String.valueOf(eventProperties.get("currency"))
                );
            }
        }

        Map<String, Object> userProperties = element.getUserProperties();
        if (userProperties != null) {
            for (String key : userProperties.keySet()) {
                event.addCallbackParameter(key, String.valueOf(userProperties.get(key)));
            }
        }

        Adjust.getDefaultInstance().trackEvent(event);
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
    public Map<String, Object> getDestinationProps(RudderElement element) {
        String token = settings.getToken(element.getEventName());
        if (token != null) {
            Map<String, Object> destinationPropsMap = new HashMap<>();
            destinationPropsMap.put("ad_event_token", token);
            return destinationPropsMap;
        }
        return null;
    }
}
