package com.rudderlabs.android.library.models.porperties.ecommerce

import com.google.gson.annotations.SerializedName

class ECommerceCoupon(
    @SerializedName("cart_id") val cartId: String = "",
    @SerializedName("order_id") val orderId: String = "",
    @SerializedName("coupon_id") val couponId: String = ""
) {
    @SerializedName("coupon_name")
    var couponName: String = ""
    @SerializedName("discount")
    var discount: Float = 0f
    @SerializedName("reason")
    var reason: String = ""
}