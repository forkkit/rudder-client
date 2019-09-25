package com.rudderlabs.android.sdk.core;

import com.google.gson.annotations.SerializedName;

class RudderServerDestinationDefinition {
    @SerializedName("name")
    String definitionName;
    @SerializedName("updatedAt")
    String updatedAt;
}
