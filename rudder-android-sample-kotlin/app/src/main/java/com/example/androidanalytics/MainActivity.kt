package com.example.androidanalytics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rudderlabs.android.library.models.RudderEventBuilder
import com.rudderlabs.android.library.models.porperties.Property
import com.rudderlabs.android.library.models.porperties.TypeValuePair
import com.rudderlabs.android.library.models.porperties.ecommerce.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenViewBtn.setOnClickListener {
            val rudderEvent = RudderEventBuilder()
                .setEvent("screenview")
                .setChannel("Android Test Channel")
                .setType("screen")
                .setProperty(Property().also {
                    it.addProperty("name", "Home Screen View")
                }).build()
            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productSearchBtn.setOnClickListener {
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCTS_SEARCHED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(
                    ECommercePropertyBuilder()
                        .addQuery(queryField.text.toString())
                        .build()
                )
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productListViewBtn.setOnClickListener {
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_LIST_VIEWED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(
                    ECommercePropertyBuilder()
                        .addListId("hot_deals_1")
                        .addCategory("Deals")
                        .addProduct(
                            ECommerceProduct(
                                productId = "507f1f77bcf86cd799439011",
                                sku = "45790-32",
                                category = "Games",
                                name = "Monopoly: 3rd Edition",
                                brand = "Monopoly",
                                variant = "Single User",
                                price = 19f,
                                quantity = 1f,
                                coupon = "MAY_DEALS_3",
                                position = 1,
                                url = "https://www.example.com/product/path",
                                imageUrl = "https://www.example.com/product/path.jpg"
                            )
                        )
                        .build()
                )
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productListFilteredBtn.setOnClickListener {
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_LIST_FILTERED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(
                    ECommercePropertyBuilder()
                        .addListId("todays_deals_may_11_2016")
                        .addCategory("Deals")
                        .addProducts(
                            listOf(
                                ECommerceProduct(
                                    productId = "507f1f77bcf86cd799439011",
                                    sku = "45790-32",
                                    category = "Games",
                                    name = "Monopoly: 3rd Edition",
                                    brand = "Monopoly",
                                    variant = "Single User",
                                    price = 19f,
                                    quantity = 1f,
                                    coupon = "MAY_DEALS_3",
                                    position = 1,
                                    url = "https://www.example.com/product/path",
                                    imageUrl = "https://www.example.com/product/path.jpg"
                                )
                            )
                        )
                        .addFilters(
                            listOf(
                                TypeValuePair(type = "department", value = "beauty"),
                                TypeValuePair(type = "price", value = "under-$25")
                            )
                        )
                        .addSortItems(
                            listOf(
                                TypeValuePair(type = "price", value = "desc")
                            )
                        )
                        .build()
                )
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        promotionViewed.setOnClickListener {
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PROMOTION_VIEWED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(
                    ECommercePropertyBuilder()
                        .addPromotion(
                            ECommercePromotion(
                                promotionId = "promo_1",
                                creative = "top_banner_2",
                                name = "75% store-wide shoe sale",
                                position = "home_banner_top"
                            )
                        )
                        .build()
                )
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        promotionClickedBtn.setOnClickListener {
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PROMOTION_CLICKED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(
                    ECommercePropertyBuilder()
                        .addPromotion(
                            ECommercePromotion(
                                promotionId = "promo_1",
                                creative = "top_banner_2",
                                name = "75% store-wide shoe sale",
                                position = "home_banner_top"
                            )
                        ).build()
                ).build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productClickedBtn.setOnClickListener {
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_CLICKED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(
                    ECommercePropertyBuilder()
                        .addProductViewed(
                            ECommerceProduct(
                                productId = "507f1f77bcf86cd799439011",
                                sku = "45790-32",
                                category = "Games",
                                name = "Monopoly: 3rd Edition",
                                brand = "Monopoly",
                                variant = "Single User",
                                price = 19f,
                                quantity = 1f,
                                coupon = "MAY_DEALS_3",
                                position = 1,
                                url = "https://www.example.com/product/path",
                                imageUrl = "https://www.example.com/product/path.jpg"
                            )
                        ).build()
                ).build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productViewedBtn.setOnClickListener {
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_VIEWED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(
                    ECommercePropertyBuilder()
                        .addProductViewed(
                            ECommerceProduct(
                                productId = "507f1f77bcf86cd799439011",
                                sku = "45790-32",
                                category = "Games",
                                name = "Monopoly: 3rd Edition",
                                brand = "Monopoly",
                                variant = "Single User",
                                price = 19f,
                                quantity = 1f,
                                coupon = "MAY_DEALS_3",
                                position = 1,
                                url = "https://www.example.com/product/path",
                                imageUrl = "https://www.example.com/product/path.jpg"
                            )
                        ).build()
                ).build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productAddedBtn.setOnClickListener {
            val builder = ECommerceCartBuilder.instance()
                .createCart("skdjsidjsdkdj29j")
                .addProductToCart(
                    ECommerceProduct(
                        productId = "507f1f77bcf86cd799439011",
                        sku = "45790-32",
                        category = "Games",
                        name = "Monopoly: 3rd Edition",
                        brand = "Monopoly",
                        variant = "Single User",
                        price = 19f,
                        quantity = 1f,
                        coupon = "MAY_DEALS_3",
                        position = 1,
                        url = "https://www.example.com/product/path",
                        imageUrl = "https://www.example.com/product/path.jpg"
                    )
                )

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_ADDED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildProductProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productRemovedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
                .createCart("skdjsidjsdkdj29j")
                .addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))

            builder.removeProductFromCart(dummyProduct)

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_REMOVED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildProductProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productRemovedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
                .createCart("skdjsidjsdkdj29j")
                .addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))

            builder.removeProductFromCart(dummyProduct)

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_REMOVED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildProductProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        cartViewedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
                .createCart("skdjsidjsdkdj29j")
                .addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.CART_VIEWED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildCartProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        checkoutStartedButton.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()

            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.startCheckout(checkoutId = "50314b8e9bcf000000000000")

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.CART_VIEWED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildOrderProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        checkoutStepViewedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()

            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.startCheckout(checkoutId = "50314b8e9bcf000000000000")
            builder.updateCheckout(
                step = 2,
                shippingMethod = "Fedex",
                paymentMethod = "Visa"
            )

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.CHECKOUT_STEP_VIEWED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildCheckoutProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        checkoutStepCompletedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.startCheckout(checkoutId = "50314b8e9bcf000000000000")
            builder.updateCheckout(
                step = 2,
                shippingMethod = "Fedex",
                paymentMethod = "Visa"
            )
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.CHECKOUT_STEP_COMPLETED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildCheckoutProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        paymentInfoEnteredBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.startCheckout(checkoutId = "50314b8e9bcf000000000000")
            builder.updateCheckout(
                step = 2,
                shippingMethod = "Fedex",
                paymentMethod = "Visa"
            )

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PAYMENT_INFO_ENTERED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildCheckoutProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        orderUpdatedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.startCheckout(checkoutId = "50314b8e9bcf000000000000")
            builder.updateOrder(
                affiliation = "Google Store",
                total = 27.50f,
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.ORDER_UPDATED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildOrderProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        orderCompletedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.startCheckout(checkoutId = "50314b8e9bcf000000000000")
            builder.updateOrder(
                affiliation = "Google Store",
                total = 27.50f,
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.ORDER_COMPLETED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildOrderProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        orderRefundedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.startCheckout(checkoutId = "50314b8e9bcf000000000000")
            builder.updateOrder(
                affiliation = "Google Store",
                total = 27.50f,
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.ORDER_REFUNDED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildOrderProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        orderCancelledBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.startCheckout(checkoutId = "50314b8e9bcf000000000000")
            builder.updateOrder(
                affiliation = "Google Store",
                total = 27.50f,
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.ORDER_CANCELLED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildOrderProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        couponEnteredBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.addCoupon("may_deals_2016")

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.COUPON_ENTERED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildCouponProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        couponAppliedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.applyCoupon("May Deals 2016", 23.32f)

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.COUPON_APPLIED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildCouponProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        couponDeniedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.applyCoupon("May Deals 2016", 23.32f)
            builder.couponDenied("Coupon expired")

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.COUPON_DENIED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildCouponProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        couponRemovedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("skdjsidjsdkdj29j").addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            builder.createOrder(
                orderId = "50314b8e9bcf000000000000",
                affiliation = "Google Store",
                value = 30f,
                revenue = 25.00f,
                shippingCost = 3f,
                tax = 2f,
                discount = 2.5f,
                coupon = "hasbros",
                currency = "USD"
            )
            builder.applyCoupon("May Deals 2016", 23.32f)
            builder.removeCoupon()

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.COUPON_REMOVED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildCouponProperty())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        addToWishListBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createWishList("skdjsidjsdkdj29j", "Loved Games")
            builder.addProductToWishList(dummyProduct)
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_ADDED_TO_WISH_LIST.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildForWishList())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        removeFromWishListBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createWishList("skdjsidjsdkdj29j", "Loved Games")
            builder.addProductToWishList(dummyProduct)
            builder.removeProductFromWishList(dummyProduct)
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_REMOVED_FROM_WISH_LIST.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildForWishList())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        wishListToCartBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createWishList("skdjsidjsdkdj29j", "Loved Games")
            builder.addProductToWishList(dummyProduct)
            builder.createCart("skdjsidjsdkdj29j")
            builder.addWishListProductToCart()

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.WISH_LIST_PRODUCT_ADDED_TO_CART.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(builder.buildForWishListCart())
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productSharedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val property = ECommerceCartBuilder.instance().buildForProductShare(
                shareVia = "email",
                shareMessage = "Hey, check out this item",
                recipient = "friend@gmail.com",
                product = dummyProduct
            )

            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_SHARED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(property)
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        cartSharedBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            builder.createCart("d92jd29jd92jd29j92d92jd")
                .addProductsToCart(listOf(dummyProduct, dummyProduct, dummyProduct))
            val property = builder.buildForCartSharing(
                shareVia = "email",
                shareMessage = "Hey, check out this item",
                recipient = "friend@gmail.com"
            )
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.CART_SHARED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(property)
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        productReviewdBtn.setOnClickListener {
            val dummyProduct = ECommerceProduct(
                productId = "507f1f77bcf86cd799439011",
                sku = "45790-32",
                category = "Games",
                name = "Monopoly: 3rd Edition",
                brand = "Monopoly",
                variant = "Single User",
                price = 19f,
                quantity = 1f,
                coupon = "MAY_DEALS_3",
                position = 1,
                url = "https://www.example.com/product/path",
                imageUrl = "https://www.example.com/product/path.jpg"
            )

            val builder = ECommerceCartBuilder.instance()
            val property = builder.buildForProductReview(
                product = dummyProduct,
                reviewId = "kdfjrj39fj39jf3",
                reviewBody = "I love this product",
                rating = "5"
            )
            val rudderEvent = RudderEventBuilder()
                .setEvent(ECommerceEvents.PRODUCT_REVIEWED.value)
                .setChannel("Test Channel")
                .setType("Test Type")
                .setProperty(property)
                .build()

            MainApplication.instance?.getRudderInstance()?.track(event = rudderEvent)
        }

        flushButton.setOnClickListener {
            MainApplication.instance?.getRudderInstance()?.flush()
        }
    }
}
