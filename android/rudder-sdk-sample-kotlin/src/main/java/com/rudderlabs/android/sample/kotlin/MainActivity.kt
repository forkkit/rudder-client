package com.rudderlabs.android.sample.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rudderlabs.android.sdk.core.RudderElementBuilder
import com.rudderlabs.android.sdk.core.TrackPropertyBuilder
import com.rudderlabs.android.sdk.ecomm.*
import com.rudderlabs.android.sdk.ecomm.events.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dummyProduct: ECommerceProduct = ECommerceProduct.Builder()
            .withProductId("test_product_id")
            .withBrand("Dummy Brand")
            .withCategory("Dummy Category")
            .withCurrency("USD")
            .withName("Dummy Product Name")
            .withPrice(10f)
            .withQuantity(1f)
            .build()

        val dummyCart: ECommerceCart = ECommerceCart.Builder()
            .withCartId("dummy_cart_id")
            .withProducts(dummyProduct, dummyProduct, dummyProduct)
            .build()

        val dummyCoupon: ECommerceCoupon = ECommerceCoupon.Builder()
            .withCartId("dummy_cart_id")
            .withCouponId("dummy_coupon_id")
            .withCouponName("Dummy Coupon Name")
            .withOrderId("test_order_id")
            .withDiscount(5f)
            .withReason("Test Reason")
            .build()

        val dummyFilter: ECommerceFilter = ECommerceFilter.Builder()
            .withType("Test Filter Type")
            .withValue("Test Filter Value")
            .build()

        val dummySort: ECommerceSort = ECommerceSort.Builder()
            .withType("Test Sort Type")
            .withValue("Test Sort Value")
            .build()

        val dummyOrder: ECommerceOrder = ECommerceOrder.Builder()
            .withAffiliation("Test Affiliation")
            .withCoupon("Dummy Coupon")
            .withCurrency("USD")
            .withDiscount(5f)
            .withOrderId("test_order_id")
            .withProducts(dummyProduct, dummyProduct, dummyProduct)
            .withRevenue(40f)
            .withShippingCost(10f)
            .withTax(5f)
            .withTotal(50f)
            .withValue(45f)
            .build()

        val dummyPromotion: ECommercePromotion = ECommercePromotion.Builder()
            .withCreative("Test Creative")
            .withName("Test Name")
            .withPosition("top_banner")
            .withPromotionId("test_promotion_id")
            .build()

        val dummyWishList: ECommerceWishList = ECommerceWishList.Builder()
            .withWishListId("test_wish_list_id")
            .withWishListName("Test Wish List Name")
            .build()

        val dummyCheckout: ECommerceCheckout = ECommerceCheckout.Builder()
            .withCheckoutId("test_checkout_id")
            .withOrderId("test_order_id")
            .withPaymentMethod("Visa")
            .withShippingMethod("Fedex")
            .withStep(1)
            .build()


        btn.setOnClickListener {
            MainApplication.rudderEcommClient.client.track(
                RudderElementBuilder()
                    .setEventName("test_event")
                    .setProperty(
                        TrackPropertyBuilder()
                            .setCategory("test_category")
                            .build()
                    )
                    .setUserId("test_user_id")
            )

            MainApplication.rudderEcommClient.track(
                CartSharedEvent()
                    .withCart(dummyCart)
                    .withRecipient("friend@gmail.com")
                    .withShareMessage("Test Share Message")
                    .withSocialChannel("Email")
            )

            MainApplication.rudderEcommClient.track(
                CartViewedEvent()
                    .withCart(dummyCart)
            )

            MainApplication.rudderEcommClient.track(
                CheckoutStartedEvent()
                    .withCheckout(dummyCheckout)
                    .withOrder(dummyOrder)
            )

            MainApplication.rudderEcommClient.track(
                CheckoutStepCompletedEvent()
                    .withCheckout(dummyCheckout)
            )

            MainApplication.rudderEcommClient.track(
                CheckoutStepViewedEvent()
                    .withCheckout(dummyCheckout)
            )

            MainApplication.rudderEcommClient.track(
                CouponAppliedEvent()
                    .withCoupon(dummyCoupon)
            )

            MainApplication.rudderEcommClient.track(
                CouponDeniedEvent()
                    .withCoupon(dummyCoupon)
            )

            MainApplication.rudderEcommClient.track(
                CouponEnteredEvent()
                    .withCoupon(dummyCoupon)
            )

            MainApplication.rudderEcommClient.track(
                CouponRemovedEvent()
                    .withCoupon(dummyCoupon)
            )

            MainApplication.rudderEcommClient.track(
                OrderCancelledEvent()
                    .withOrder(dummyOrder)
            )

            MainApplication.rudderEcommClient.track(
                OrderCompletedEvent()
                    .withOrder(dummyOrder)
            )

            MainApplication.rudderEcommClient.track(
                OrderCompletedEvent()
                    .withOrder(dummyOrder)
            )

            MainApplication.rudderEcommClient.track(
                OrderRefundedEvent()
                    .withOrder(dummyOrder)
                    .withRefundValue(40f)
            )

            MainApplication.rudderEcommClient.track(
                OrderRefundedEvent()
                    .withOrder(dummyOrder)
                    .withProducts(dummyProduct, dummyProduct)
                    .withRefundValue(40f)
            )

            MainApplication.rudderEcommClient.track(
                OrderUpdatedEvent()
                    .withOrder(dummyOrder)
            )

            MainApplication.rudderEcommClient.track(
                PaymentInfoEnteredEvent()
                    .withCheckout(dummyCheckout)
            )

            MainApplication.rudderEcommClient.track(
                ProductAddedToCartEvent()
                    .withCartId(dummyCart.cartId)
                    .withProduct(dummyProduct)
            )

            MainApplication.rudderEcommClient.track(
                ProductAddedToWishListEvent()
                    .withWishList(dummyWishList)
                    .withProduct(dummyProduct)
            )

            MainApplication.rudderEcommClient.track(
                ProductClickedEvent()
                    .withProduct(dummyProduct)
            )

            MainApplication.rudderEcommClient.track(
                ProductListFilteredEvent()
                    .withCategory(dummyProduct.category)
                    .withFilters(dummyFilter, dummyFilter)
                    .withSorts(dummySort, dummySort)
                    .withListId("test_sorted_id")
                    .withProducts(dummyProduct, dummyProduct, dummyProduct)
            )

            MainApplication.rudderEcommClient.track(
                ProductListViewedEvent()
                    .withCategory("Test Category")
                    .withListId("test_list_id")
                    .withProducts(dummyProduct, dummyProduct, dummyProduct)
            )

            MainApplication.rudderEcommClient.track(
                ProductRemovedEvent()
                    .withProduct(dummyProduct)
            )

            MainApplication.rudderEcommClient.track(
                ProductRemovedFromWishListEvent()
                    .withWishList(dummyWishList)
                    .withProduct(dummyProduct)
            )

            MainApplication.rudderEcommClient.track(
                ProductReviewedEvent()
                    .withProduct(dummyProduct)
                    .withRating("4.0")
                    .withReviewBody("Test Review Body")
                    .withReviewId("test_review_id")
            )

            MainApplication.rudderEcommClient.track(
                ProductSearchedEvent()
                    .withQuery("blue hotpants")
            )

            MainApplication.rudderEcommClient.track(
                ProductSharedEvent()
                    .withProduct(dummyProduct)
                    .withRecipient("friend@gmail.com")
                    .withShareMessage("Test Share Message")
                    .withSocialChannel("Email")
            )

            MainApplication.rudderEcommClient.track(
                ProductViewedEvent()
                    .withProduct(dummyProduct)
            )

            MainApplication.rudderEcommClient.track(
                PromotionClickedEvent()
                    .withPromotion(dummyPromotion)
            )

            MainApplication.rudderEcommClient.track(
                PromotionViewedEvent()
                    .withPromotion(dummyPromotion)
            )

            MainApplication.rudderEcommClient.track(
                WishListProductAddedToCartEvent()
                    .withCart(dummyCart)
                    .withProduct(dummyProduct)
                    .withWishList(dummyWishList)
            )

            count += 1
            textView.text = "Count: $count"
        }

        rst.setOnClickListener {
            count = 0
            textView.text = "Count: "
        }
    }
}
