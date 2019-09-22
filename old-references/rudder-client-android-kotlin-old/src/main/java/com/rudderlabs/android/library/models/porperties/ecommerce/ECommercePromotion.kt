package com.rudderlabs.android.library.models.porperties.ecommerce

import com.google.gson.annotations.SerializedName

class ECommercePromotion(
    @SerializedName("promotion_id") var promotionId: String = "",
    @SerializedName("creative") var creative: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("position") var position: String = ""
)