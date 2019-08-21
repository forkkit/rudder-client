package com.rudderlabs.android.sdk.integrations.appsflyer;

import android.app.Application;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.rudderlabs.android.sdk.core.*;

import java.util.HashMap;
import java.util.Map;

public class AppsflyerIntegration extends RudderIntegrationFactory<AppsFlyerLib> implements AppsFlyerConversionListener {
    private RudderClient rudderClient;
    private Application application;

    public AppsflyerIntegration(String token) {
        this(AppsflyerSettings.getWithToken(token));
    }

    public AppsflyerIntegration(AppsflyerSettings settings) {
        super(settings);
    }

    @Override
    public void init(Application application, RudderClient client, RudderConfig config) {
        this.rudderClient = client;
        this.application = application;
        AppsFlyerLib.getInstance().init(getSettings().getToken(), this, application);
        AppsFlyerLib.getInstance().startTracking(application);
    }

    @Override
    public void identify(RudderElement element) {

    }

    @Override
    public void page(RudderElement element) {
        AppsFlyerLib.getInstance()
                .trackEvent(
                        application,
                        "page-" + (element.getEventName() == null ? "" : element.getEventName()),
                        element.getEventProperties()
                );
    }

    @Override
    public void screen(RudderElement element) {
        AppsFlyerLib.getInstance()
                .trackEvent(
                        application,
                        "screen-" + (element.getEventName() == null ? "" : element.getEventName()),
                        element.getEventProperties()
                );
    }

    @Override
    public void track(RudderElement element) {
//        if (getSettings().nativeSDKEnabaled()) {
        AppsFlyerLib.getInstance()
                .trackEvent(
                        application,
                        "track-" + element.getEventName(),
                        element.getEventProperties()
                );
//        }
    }

    @Override
    public String key() {
        return "AF";
    }

    @Override
    public boolean enabled() {
        return true;
    }

    @Override
    public AppsFlyerLib getInstance() {
        return AppsFlyerLib.getInstance();
    }

    private Map<String, Object> props;

    @Override
    public Map<String, Object> getDestinationProps() {
        if (props == null) {
            props = new HashMap<>();
            props.put("rl_af_uid", AppsFlyerLib.getInstance().getAppsFlyerUID(application));
        }
        return props;
    }

    @Override
    public void onInstallConversionDataLoaded(Map<String, String> map) {

    }

    @Override
    public void onInstallConversionFailure(String s) {

    }

    @Override
    public void onAppOpenAttribution(Map<String, String> map) {

    }

    @Override
    public void onAttributionFailure(String s) {

    }
}
