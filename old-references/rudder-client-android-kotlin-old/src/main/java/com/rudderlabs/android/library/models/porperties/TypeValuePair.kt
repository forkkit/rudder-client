package com.rudderlabs.android.library.models.porperties

import com.google.gson.annotations.SerializedName

class TypeValuePair(
    @SerializedName("type") val type: String = "",
    @SerializedName("value") val value: String = ""
)