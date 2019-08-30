package com.rudderlabs.android.sdk.integrations.mixpanel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rudderlabs.android.sdk.core.MessageType;
import com.rudderlabs.android.sdk.core.RudderElement;
import com.rudderlabs.android.sdk.core.RudderTraits;
import com.rudderlabs.android.sdk.core.ecommerce.ECommerceEvents;
import com.rudderlabs.android.sdk.core.ecommerce.ECommerceParamNames;
import com.rudderlabs.android.sdk.core.ecommerce.ECommerceProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MixpanelEventProcessor {

    static Gson gson = new Gson();

    /*JSONObject json = {
            "rl_user_properties.createdAt":"$created",
            "rl_user_properties.email":"$email",
            "rl_user_properties.firstName":"$firstName",
            "rl_user_properties.lastName":"$lastName",
            "rl_user_properties.name":"$name",
            "rl_user_properties.username":"$username",
            "rl_user_properties.phone":"$phone"
};*/
    static Map<String, String> specialProperties = new HashMap<String, String>() {{
        put("rl_createdat", "$created");
        put("rl_email", "$email");
        put("rl_firstname", "$firstName");
        put("rl_lastname", "$lastName");
        put("rl_name", "$name");
        put("rl_username", "$username");
        put("rl_phone", "$phone");
    }};



    static MixpanelEvent processRudderEvents(RudderElement element) throws JSONException {
        String eventType = element.getType().toLowerCase();
        switch (eventType) {
            case MessageType.TRACK:
                return processTrackEvents(element);
            case MessageType.PAGE:
                return processPageEvents(element);
            case MessageType.SCREEN:
                return processScreenEvents(element);
            case MessageType.IDENTIFY:
                return processIdentifyEvents(element);
        }
        return null;
    }

    private static MixpanelEvent processTrackEvents(RudderElement element) throws JSONException {
        String eventType = element.getType().toLowerCase();
        String eventName = element.getEventName();
        MixpanelEvent mixpanelEvent = new MixpanelEvent(eventName);
        mixpanelEvent.setEventType(eventType);
        Map<String, Object> property = element.getProperties();

        if(property.containsKey("revenue")){
            mixpanelEvent.setRevenueEvent(true);
            //mixpanelEvent.setDistinctId(property.get("").toString());//TODO
            mixpanelEvent.setRevenue(property.get("revenue").toString());

            Map<String, Object> trackChargeProperty = new HashMap<>();
            //trackChargeProperty.put("$time", property.get(""));//TODO
            //mixpanelEvent.getTrackChargeProperty().put("$time", property.get(""));//TODO
            mixpanelEvent.setTrackChargeProperty(new JSONObject(gson.toJson(trackChargeProperty)));
        }

        mixpanelEvent.setEventProps(new JSONObject(gson.toJson(property)));
        return mixpanelEvent;
    }

    private static MixpanelEvent processPageEvents(RudderElement element) throws JSONException {
        MixpanelEvent mixpanelEvent = new MixpanelEvent("page");
        mixpanelEvent.setEventType(element.getType().toLowerCase());
        mixpanelEvent.setEventProps(new JSONObject(gson.toJson(element.getProperties())));
        return mixpanelEvent;
    }

    private static MixpanelEvent processScreenEvents(RudderElement element) throws JSONException {
        MixpanelEvent mixpanelEvent = new MixpanelEvent("screen");
        mixpanelEvent.setEventType(element.getType().toLowerCase());
        mixpanelEvent.setEventProps(new JSONObject(gson.toJson(element.getProperties())));
        return mixpanelEvent;
    }

    private static MixpanelEvent processIdentifyEvents(RudderElement element) throws JSONException {
        MixpanelEvent mixpanelEvent = new MixpanelEvent("identify");
        mixpanelEvent.setEventType(element.getType().toLowerCase());

        RudderTraits traits = element.getTraits();
        //mixpanelEvent.setEventProps(new JSONObject(gson.toJson(traits)));
        JSONObject jsonTraits = new JSONObject(gson.toJson(traits));
        Map<String, Object> eventProperties = new HashMap<>();
        for (Iterator key = jsonTraits.keys(); key.hasNext();) {
            String keyString = key.next().toString();
            Object value = jsonTraits.get(keyString);
            String mixPanelKey = specialProperties.get(keyString) != null ? specialProperties.get(keyString) : keyString;
            eventProperties.put(mixPanelKey, value);
        }
        mixpanelEvent.setEventProps(new JSONObject(gson.toJson(eventProperties)));
        return mixpanelEvent;
    }

}
