package com.rudderlabs.android.sdk.integrations.mixpanel;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.rudderlabs.android.sdk.core.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MixpanelIntegration extends RudderIntegrationFactory<MixpanelAPI> {

    private RudderClient rudderClient;
    private Application application;
    private MixpanelAPI mixpanel;


    public MixpanelIntegration(RudderIntegrationSettings settings) {
        super(settings);
    }

    public MixpanelIntegration(String token) {
        this(MixpanelSettings.getWithToken(token));
    }

    @Override
    public void init(Application application, RudderClient client, RudderConfig config) {

        this.rudderClient = client;
        this.application = application;
        /*MixpanelAPI.getInstance().init(getSettings().getToken(), this, application);
        MixpanelAPI.getInstance().startTracking(application);*/
        mixpanel = MixpanelAPI.getInstance(application, getSettings().getToken());

    }

    @Override
    public void identify(RudderElement element) {
        processInAppEvents(element);
    }

    @Override
    public void page(RudderElement element) {
        processInAppEvents(element);
    }

    @Override
    public void screen(RudderElement element) {
        processInAppEvents(element);
    }

    @Override
    public void track(RudderElement element) {
        processInAppEvents(element);
    }

    @Override
    public String key() {
        return "mixpanel";
    }

    @Override
    public boolean enabled() {
        return true;
    }

    @Override
    public MixpanelAPI getInstance() {
        return MixpanelAPI.getInstance(application, getSettings().getToken());
    }

    @Override
    public Map<String, Object> getDestinationProps(RudderElement element) {
        return null;
    }

    private void processInAppEvents(RudderElement element){
        Log.e("mixpanel: ", "in processInAppEvents");



        //============ custom hard coded event ==============
        /*String event = "test track charge";
        JSONObject eventProperties = new JSONObject();
        eventProperties.put("token", "ef1d42390426e3f7c90ac78272e74344");
        eventProperties.put("distinct_id", "13793");
        try {
            JSONObject properties = new JSONObject();
            properties.put("time", new Date());
            properties.put("mno", "XYZ");
            eventProperties.put("properties", properties);
        } catch (Exception e){
            e.printStackTrace();
        }
        /*mixpanel.getPeople().identify("13793");
        mixpanel.track(event, eventProperties);
        mixpanel.flush();

        mixpanel.getPeople().identify("123456");
        mixpanel.getPeople().trackCharge(10, null);
        mixpanel.track(event, eventProperties);

        //mixpanel.alias("123456", null);
        //mixpanel.getPeople().identify(mixpanel.getDistinctId());
        //mixpanel.getPeople().trackCharge(10, null);
        //mixpanel.getPeople().identify("123456");
        mixpanel.flush();*/

        //=====================================================





        //Log.e("mixpanel: ", "sent data to mixpanel");

        Log.e("mixpanel: ", "distinct id: " + mixpanel.getDistinctId());


        try {
            MixpanelEvent mixpanelEvent = MixpanelEventProcessor.processRudderEvents(element);
            if (mixpanelEvent != null && mixpanelEvent.getEventType() != null) {
                Log.e("mixpanel: ", "eventType: " + mixpanelEvent.getEventType());
                Log.e("mixpanel: ", "eventName: " + mixpanelEvent.getEventName());
                switch(mixpanelEvent.getEventType()) {
                    case "screen":
                    case "page":
                    case "track":

                        if (mixpanelEvent.isRevenueEvent()) {
                            Log.e("mixpanel: ", "revenue event: " + mixpanelEvent.isRevenueEvent());
                            mixpanel.getPeople().identify(mixpanel.getDistinctId());
                            mixpanel.getPeople().trackCharge(Double.parseDouble(mixpanelEvent.getRevenue()), mixpanelEvent.getTrackChargeProperty());
                        }
                        mixpanel.track(mixpanelEvent.getEventName(), mixpanelEvent.getEventProps());
                        break;
                    case "identify":

                        JSONObject eventProperties = mixpanelEvent.getEventProps();
                        mixpanel.getPeople().identify(mixpanel.getDistinctId());

                        Log.e("mispanel: " , "identify props: " + mixpanelEvent.getEventProps().toString());

                        for (Iterator key = eventProperties.keys(); key.hasNext();) {
                            String keyString = key.next().toString();
                            Object value = eventProperties.get(keyString);
                            mixpanel.getPeople().set(keyString, value);

                        }
                        break;
                }
                Log.e("mixpanel: ", "sent data to mixpanel");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
