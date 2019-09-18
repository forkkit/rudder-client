package com.rudderlabs.android.library.models.porperties.ecommerce

import com.google.gson.annotations.SerializedName

class ECommerceCheckout(
    @SerializedName("checkout_id") val checkoutId: String = "",
    @SerializedName("order_id") val orderId: String = ""
) {
    @SerializedName("step")
    var step: Int = 0
    @SerializedName("shipping_method")
    var shippingMethod: String = ""
    @SerializedName("payment_method")
    var paymentMethod: String = ""
}