package com.rudderlabs.android.sdk.core.event;

import android.os.Build;
import com.google.gson.annotations.SerializedName;
import com.rudderlabs.android.sdk.core.BuildConfig;

class RudderOSInfo {
    @SerializedName("rl_name")
    private String name = "Android";
    @SerializedName("rl_version")
    private String version = Build.VERSION.RELEASE;
}
