package com.rudderlabs.android.library.models.porperties.ecommerce

import com.google.gson.annotations.SerializedName

open class ECommerceProduct(
    @SerializedName("product_id") val productId: String = "",
    @SerializedName("sku") val sku: String = "",
    @SerializedName("category") val category: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("brand") val brand: String = "",
    @SerializedName("variant") val variant: String = "",
    @SerializedName("price") val price: Float = 0f,
    @SerializedName("quantity") val quantity: Float = 0f,
    @SerializedName("coupon") val coupon: String = "",
    @SerializedName("position") val position: Int = 0,
    @SerializedName("url") val url: String = "",
    @SerializedName("image_url") val imageUrl: String = ""
)

class ECommerceProductBuilder() {
    private var productId: String = ""
    fun setProductId(productId: String): ECommerceProductBuilder {
        this.productId = productId
        return this
    }

    private var sku: String = ""
    fun setSku(sku: String): ECommerceProductBuilder {
        this.sku = sku
        return this
    }

    private var category: String = ""
    fun setCategory(category: String): ECommerceProductBuilder {
        this.category = category
        return this
    }


    private var name: String = ""
    fun setName(name: String): ECommerceProductBuilder {
        this.name = name
        return this
    }

    private var brand: String = ""
    fun setBrand(brand: String): ECommerceProductBuilder {
        this.brand = brand
        return this
    }

    private var variant: String = ""
    fun setVariant(variant: String): ECommerceProductBuilder {
        this.variant = variant
        return this
    }

    private var price: Float = 0f
    fun setPrice(price: Float): ECommerceProductBuilder {
        this.price = price
        return this
    }

    private var quantity: Float = 0f
    fun setQuantity(quantity: Float): ECommerceProductBuilder {
        this.quantity = quantity
        return this
    }

    private var coupon: String = ""
    fun setCoupon(coupon: String): ECommerceProductBuilder {
        this.coupon = coupon
        return this
    }


    private var position: Int = 0
    fun setPosition(position: Int): ECommerceProductBuilder {
        this.position = position
        return this
    }

    private var url: String = ""
    fun setUrl(url: String): ECommerceProductBuilder {
        this.url = url
        return this
    }

    private var imageUrl: String = ""
    fun setImageUrl(imageUrl: String): ECommerceProductBuilder {
        this.imageUrl = imageUrl
        return this
    }

    fun build(): ECommerceProduct {
        return ECommerceProduct(
            productId = productId,
            sku = sku,
            category = category,
            name = name,
            brand = brand,
            variant = variant,
            price = price,
            quantity = quantity,
            coupon = coupon,
            position = position,
            url = url,
            imageUrl = imageUrl
        )
    }
}
