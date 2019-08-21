package com.rudderlabs.android.sdk.core;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RudderPayload {
    @SerializedName("sent_at")
    private String sentAt;
    @SerializedName("batch")
    private ArrayList<RudderElement> events;
    @SerializedName("writeKey")
    private String writeKey;

    public RudderPayload(ArrayList<RudderElement> events, String writeKey) {
        this.events = events;
        this.writeKey = writeKey;
    }
}
