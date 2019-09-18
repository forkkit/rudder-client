package com.rudderlabs.android.library.models.porperties.ecommerce

import com.google.gson.annotations.SerializedName

internal class ECommerceCart(
    @SerializedName("cart_id") var cartId: String = "",
    @SerializedName("products") var products: MutableList<ECommerceProduct> = mutableListOf()
) {
    fun addProduct(product: ECommerceProduct) {
        products.add(product)
    }

    fun addProducts(products: List<ECommerceProduct>) {
        this.products.addAll(products)
    }

    fun removeProduct(product: ECommerceProduct): Boolean {
        val index = products.indexOfFirst { product.productId == it.productId }
        if (index == -1) return false
        else products.removeAt(index)
        return true
    }
}