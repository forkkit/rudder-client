package com.rudderlabs.android.library.models

import com.rudderlabs.android.library.util.Utils
import com.google.gson.annotations.SerializedName

data class RudderEventPayload(
    @SerializedName("sent_at")
    val timestamp: String = Utils.getDate(),
    @SerializedName("batch")
    val events: List<RudderEvent>
)