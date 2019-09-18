package com.rudderlabs.android.library.models.porperties.ecommerce

import com.google.gson.annotations.SerializedName

class ECommerceWishlist(
    @SerializedName("wishlist_id") val wishListId: String = "",
    @SerializedName("wishlist_name") val wishListName: String = ""
)