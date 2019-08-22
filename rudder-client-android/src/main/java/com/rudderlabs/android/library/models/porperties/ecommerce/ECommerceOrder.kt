package com.rudderlabs.android.library.models.porperties.ecommerce

import com.google.gson.annotations.SerializedName

class ECommerceOrder(
        @SerializedName("order_id") val orderId: String = "",
        @SerializedName("affiliation") var affiliation: String = "",
        @SerializedName("total") var total: Float = 0f,
        @SerializedName("value") var value: Float = 0f,
        @SerializedName("revenue") var revenue: Float = 0f,
        @SerializedName("shipping") var shippingCost: Float = 0f,
        @SerializedName("tax") var tax: Float = 0f,
        @SerializedName("discount") var discount: Float = 0f,
        @SerializedName("coupon") var coupon: String = "",
        @SerializedName("currency") var currency: String = "",
        @SerializedName("products") var products: List<ECommerceProduct> = listOf()
)
