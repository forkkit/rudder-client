package com.rudderlabs.android.library.models.porperties.ecommerce

import com.rudderlabs.android.library.models.porperties.RudderProperty
import com.rudderlabs.android.library.models.porperties.RudderPropertyBuilder
import com.rudderlabs.android.library.models.porperties.TypeValuePair
import com.rudderlabs.android.library.util.convertToMap
import com.rudderlabs.android.library.util.merge

class ECommerceProperty : RudderProperty()

class ECommercePropertyBuilder : RudderPropertyBuilder() {
    companion object {
        internal const val QUERY_KEY = "query"
        internal const val LIST_ID_KEY = "list_id"
        internal const val CATEGORY_KEY = "category"
        internal const val PRODUCTS_KEY = "products"
        internal const val FILTERS_KEY = "filters"
        internal const val SORTS_KEY = "sorts"
    }

    private val eCommerceProperty: ECommerceProperty by lazy {
        ECommerceProperty()
    }

    fun addQuery(query: String): ECommercePropertyBuilder {
        eCommerceProperty.addProperty(QUERY_KEY, query)
        return this
    }

    fun addListId(listId: String): ECommercePropertyBuilder {
        eCommerceProperty.addProperty(LIST_ID_KEY, listId)
        return this
    }

    fun addCategory(category: String): ECommercePropertyBuilder {
        eCommerceProperty.addProperty(CATEGORY_KEY, category)
        return this
    }

    fun addProduct(product: ECommerceProduct): ECommercePropertyBuilder {
        eCommerceProperty.addListProperty<ECommerceProduct>(PRODUCTS_KEY)
        eCommerceProperty.addListItem(PRODUCTS_KEY, product)
        return this
    }

    fun addProducts(products: List<ECommerceProduct>): ECommercePropertyBuilder {
        eCommerceProperty.addListProperty<ECommerceProduct>(PRODUCTS_KEY)
        eCommerceProperty.addListItems(PRODUCTS_KEY, products)
        return this
    }

    fun addFilter(filter: TypeValuePair): ECommercePropertyBuilder {
        eCommerceProperty.addListProperty<TypeValuePair>(FILTERS_KEY)
        eCommerceProperty.addListItem(FILTERS_KEY, filter)
        return this
    }

    fun addFilters(filters: List<TypeValuePair>): ECommercePropertyBuilder {
        eCommerceProperty.addListProperty<TypeValuePair>(FILTERS_KEY)
        eCommerceProperty.addListItems(FILTERS_KEY, filters)
        return this
    }

    fun addSortItem(sortItem: TypeValuePair): ECommercePropertyBuilder {
        eCommerceProperty.addListProperty<TypeValuePair>(SORTS_KEY)
        eCommerceProperty.addListItem(SORTS_KEY, sortItem)
        return this
    }

    fun addSortItems(sortItems: List<TypeValuePair>): ECommercePropertyBuilder {
        eCommerceProperty.addListProperty<TypeValuePair>(SORTS_KEY)
        eCommerceProperty.addListItems(SORTS_KEY, sortItems)
        return this
    }

    fun addPromotion(promotion: ECommercePromotion): ECommercePropertyBuilder {
        eCommerceProperty.addAsMap(promotion)
        return this
    }

    fun addProductViewed(product: ECommerceProduct): ECommercePropertyBuilder {
        eCommerceProperty.addAsMap(product)
        return this
    }

    override fun build(): ECommerceProperty {
        return eCommerceProperty
    }
}

object ECommerceCartBuilder {
    private const val CART_ID_KEY = "cart_id"
    private const val ORDER_ID_KEY = "order_id"
    private const val COUPON_ID_KEY = "coupon_id"

    private var instance: ECommerceCartBuilder? = null
    @JvmStatic
    fun instance(): ECommerceCartBuilder {
        if (instance == null) {
            instance = this
        }
        return instance!!
    }

    fun invaidate() {
        instance = null
    }

    private var eCommerceCart: ECommerceCart? = null
    fun createCart(cartId: String): ECommerceCartBuilder {
        eCommerceCart = ECommerceCart(cartId)
        return instance!!
    }

    var productBuffer: ECommerceProduct? = null
    fun addProductToCart(product: ECommerceProduct): ECommerceCartBuilder {
        if (eCommerceCart == null)
            throw IllegalStateException("Cart is not initialized. call createCart(cartId) first")
        productBuffer = product
        eCommerceCart?.addProduct(product)
        return instance!!
    }

    fun addProductsToCart(products: List<ECommerceProduct>): ECommerceCartBuilder {
        if (eCommerceCart == null)
            throw IllegalStateException("Cart is not initialized. call createCart(cartId) first")
        productBuffer = products.last()
        eCommerceCart?.addProducts(products)
        return instance!!
    }

    fun removeProductFromCart(product: ECommerceProduct): ECommerceCartBuilder {
        if (eCommerceCart == null)
            throw IllegalStateException("Cart is not initialized. call createCart(cartId) first")
        if (eCommerceCart?.removeProduct(product) == true) {
            productBuffer = product
        } else {
            throw IllegalAccessException("Cart doesn't contain the product")
        }
        return instance!!
    }

    fun buildProductProperty(): ECommerceProperty {
        if (productBuffer == null) {
            throw IllegalStateException("Product is not added or removed")
        }

        return ECommerceProperty().also {
            val productMap = productBuffer.convertToMap()
            productMap[CART_ID_KEY] = eCommerceCart?.cartId

            it.addProperties(productMap)
        }
    }

    fun buildCartProperty(): ECommerceProperty {
        if (eCommerceCart == null || eCommerceCart?.products?.isEmpty() == true) {
            throw IllegalStateException("Empty cart can not be built")
        }
        val eCommerceProperty = ECommerceProperty()
        eCommerceProperty.addProperties(eCommerceCart.convertToMap())
        return eCommerceProperty
    }

    private var eCommerceOrder: ECommerceOrder? = null
    fun createOrder(
            orderId: String,
            affiliation: String,
            value: Float,
            revenue: Float,
            shippingCost: Float,
            tax: Float,
            discount: Float,
            coupon: String,
            currency: String
    ): ECommerceCartBuilder {
        if (eCommerceCart == null || eCommerceCart?.products?.isEmpty() == true) {
            throw IllegalStateException("Empty cart can't be checked out")
        }

        eCommerceOrder = ECommerceOrder(
                orderId = orderId,
                affiliation = affiliation,
                value = value,
                revenue = revenue,
                shippingCost = shippingCost,
                tax = tax,
                discount = discount,
                coupon = coupon,
                currency = currency,
                products = eCommerceCart?.products!!
        )

        return instance!!
    }

    fun updateOrder(
            affiliation: String = "",
            total: Float = 0f,
            value: Float = 0f,
            revenue: Float = 0f,
            shippingCost: Float = 0f,
            tax: Float = 0f,
            discount: Float = 0f,
            coupon: String = "",
            currency: String = ""
    ): ECommerceCartBuilder {
        if (eCommerceCart == null || eCommerceCart?.products?.isEmpty() == true) {
            throw IllegalStateException("Empty cart can't be checked out")
        }

        eCommerceOrder?.affiliation = affiliation
        eCommerceOrder?.total = total
        eCommerceOrder?.value = value
        eCommerceOrder?.revenue = revenue
        eCommerceOrder?.shippingCost = shippingCost
        eCommerceOrder?.tax = tax
        eCommerceOrder?.discount = discount
        eCommerceOrder?.coupon = coupon
        eCommerceOrder?.currency = currency

        return instance!!
    }

    private var isFullRefundStarted: Boolean = false
    fun processFullRefund(): ECommerceCartBuilder {
        if (eCommerceOrder == null || eCommerceOrder?.products?.isEmpty() == true) {
            throw IllegalStateException("Order is not created")
        }

        isFullRefundStarted = true

        return instance!!
    }

    fun buildForFullRefund(): ECommerceProperty {
        if (eCommerceOrder == null || eCommerceOrder?.products?.isEmpty() == true) {
            throw IllegalStateException("Order is not created")
        }

        if (isFullRefundStarted) {
            return ECommerceProperty().also {
                it.addProperty("order_id", eCommerceOrder?.orderId)
            }
        } else {
            throw IllegalStateException("Refund is not started")
        }
    }

    private var refundedProductList = mutableListOf<ECommerceProduct>()
    fun processPartialRefund(vararg list: ECommerceProduct): ECommerceCartBuilder {
        if (eCommerceOrder == null || eCommerceOrder?.products?.isEmpty() == true) {
            throw IllegalStateException("Order is not created")
        }

        val isProductListValid = list.all {
            it.productId in eCommerceOrder?.products?.map { p -> p.productId }!!
        }

        if (isProductListValid) {
            refundedProductList.addAll(list)
        } else {
            throw IllegalStateException("Refunded products are not in Ordered Product list")
        }

        return instance()
    }

    fun buildForPartialReturn(total: Float, currency: String): ECommerceProperty {
        if (eCommerceOrder == null || eCommerceOrder?.products?.isEmpty() == true) {
            throw IllegalStateException("Order is not created")
        } else if (refundedProductList.isEmpty()) {
            throw IllegalStateException("Partial refund is not initiated")
        }

        return ECommerceProperty().also {
            it.addProperty("order_id", eCommerceOrder?.orderId)
            it.addProperty("total", total)
            it.addProperty("currency", currency)
            it.addProperty("products", refundedProductList)
        }
    }

    private var eCommerceCheckout: ECommerceCheckout? = null
    fun startCheckout(
            checkoutId: String
    ): ECommerceCartBuilder {
        if (eCommerceOrder == null || eCommerceOrder?.products?.isEmpty() == true) {
            throw IllegalStateException("Order is not created")
        }
        eCommerceCheckout = ECommerceCheckout(checkoutId, eCommerceOrder?.orderId ?: "")
        return instance!!
    }

    fun buildOrderProperty(): ECommerceProperty {
        if (eCommerceOrder == null) throw IllegalStateException("Order Checkout is not started")

        val eCommerceProperty = ECommerceProperty()
        eCommerceProperty.addProperties(eCommerceOrder.convertToMap())
        return eCommerceProperty
    }

    fun updateCheckout(
            step: Int = 0,
            shippingMethod: String = "",
            paymentMethod: String = ""
    ): ECommerceCartBuilder {
        if (eCommerceCheckout == null) throw IllegalStateException("Order Checkout is not started")

        eCommerceCheckout?.step = step
        eCommerceCheckout?.shippingMethod = shippingMethod
        eCommerceCheckout?.paymentMethod = paymentMethod

        return instance!!
    }

    fun buildCheckoutProperty(): ECommerceProperty {
        if (eCommerceCheckout == null) throw IllegalStateException("Order Checkout is not started")

        return ECommerceProperty().also {
            it.addProperties(eCommerceCheckout.convertToMap())
        }
    }

    private var eCommerceCoupon: ECommerceCoupon? = null
    fun addCoupon(couponId: String): ECommerceCartBuilder {
        if (eCommerceOrder == null) {
            throw IllegalStateException("Order is not created")
        }

        eCommerceCoupon = ECommerceCoupon(
                orderId = eCommerceOrder?.orderId!!,
                cartId = eCommerceCart?.cartId!!,
                couponId = couponId
        )

        return instance!!
    }

    fun applyCoupon(couponName: String, discount: Float): ECommerceCartBuilder {
        if (eCommerceCoupon == null) {
            throw IllegalStateException("Coupon is not added")
        }

        eCommerceCoupon?.couponName = couponName
        eCommerceCoupon?.discount = discount

        return instance!!
    }

    fun couponDenied(reason: String): ECommerceCartBuilder {
        if (eCommerceCoupon == null) {
            throw IllegalStateException("Coupon is not added")
        }

        eCommerceCoupon?.reason = reason

        return instance!!
    }

    fun removeCoupon(): ECommerceCartBuilder {
        if (eCommerceCoupon == null) {
            throw IllegalStateException("Coupon is not added")
        }

        // support coupon removal

        return instance!!
    }

    fun buildCouponProperty(): ECommerceProperty {
        if (eCommerceCoupon == null) {
            throw IllegalStateException("Coupon is not available")
        }
        return ECommerceProperty().also {
            it.addAsMap(eCommerceCoupon)
        }
    }

    private var wishList: ECommerceWishlist? = null
    fun createWishList(wishlistId: String = "", wishListName: String = ""): ECommerceCartBuilder {
        wishList = ECommerceWishlist(wishListId = wishlistId, wishListName = wishListName)
        return instance!!
    }

    private var wishListProductBuffer: ECommerceProduct? = null
    fun addProductToWishList(product: ECommerceProduct): ECommerceCartBuilder {
        if (wishList == null) {
            throw IllegalStateException("Wish List is not created")
        }

        wishListProductBuffer = product

        return instance!!
    }

    fun removeProductFromWishList(product: ECommerceProduct): ECommerceCartBuilder {
        if (wishList == null || wishListProductBuffer == null) {
            throw IllegalStateException("Wish List is not created")
        }

        wishListProductBuffer = product

        return instance!!
    }

    fun buildForWishList(): ECommerceProperty {
        if (wishList == null || wishListProductBuffer == null) {
            throw IllegalStateException("Wish List is not created")
        }

        return ECommerceProperty().also {
            it.addProperties(
                    wishListProductBuffer.convertToMap()
                            .merge(wishList.convertToMap())
            )
        }
    }

    fun addWishListProductToCart(): ECommerceCartBuilder {
        if (eCommerceCart == null) throw IllegalStateException("Cart is not initiated")
        if (wishList == null || wishListProductBuffer == null) {
            throw IllegalStateException("Wish List is not created")
        }

        eCommerceCart?.addProduct(wishListProductBuffer!!)
        productBuffer = wishListProductBuffer

        return instance!!
    }

    fun buildForWishListCart(): ECommerceProperty {
        if (eCommerceCart == null) throw IllegalStateException("Cart is not initiated")
        if (wishList == null || wishListProductBuffer == null) {
            throw IllegalStateException("Wish List is not created")
        }

        return ECommerceProperty().also {
            it.addProperties(
                    productBuffer.convertToMap()
                            .merge(wishList.convertToMap()).also { map ->
                                map[CART_ID_KEY] = eCommerceCart?.cartId
                            }
            )
        }
    }

    fun buildForProductShare(
            shareVia: String = "",
            shareMessage: String = "",
            recipient: String = "",
            product: ECommerceProduct
    ): ECommerceProperty {
        return ECommerceProperty().also {
            it.addProperties(product.convertToMap().also { map ->
                map["share_via"] = shareVia
                map["share_message"] = shareMessage
                map["recipient"] = recipient
            })
        }
    }

    fun buildForCartSharing(
            shareVia: String = "",
            shareMessage: String = "",
            recipient: String = ""
    ): ECommerceProperty {
        if (eCommerceCart == null || eCommerceCart?.products?.isEmpty() == true) {
            throw IllegalStateException("Cart is not initialized or empty")
        }

        return ECommerceProperty().also {
            it.addProperties(eCommerceCart.convertToMap().also { map ->
                map["share_via"] = shareVia
                map["share_message"] = shareMessage
                map["recipient"] = recipient
            })
        }
    }

    fun buildForProductReview(
            product: ECommerceProduct,
            reviewId: String,
            reviewBody: String,
            rating: String
    ): ECommerceProperty {
        return ECommerceProperty().also {
            it.addProperty("product_id", product.productId)
            it.addProperty("review_id", reviewId)
            it.addProperty("review_body", reviewBody)
            it.addProperty("rating", rating)
        }
    }
}