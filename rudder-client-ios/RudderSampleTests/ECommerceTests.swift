//
//  ECommerceTests.swift
//  RudderSampleTests
//
//  Created by Arnab Pal on 18/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import XCTest
@testable import RudderSample

class ECommerceTests: BaseTests {
    let dummyProduct = ECommerceProduct(
        productId: "507f1f77bcf86cd799439011",
        sku: "45790-32",
        category: "Games",
        name: "Monopoly: 3rd Edition",
        brand: "Monopoly",
        variant: "Single User",
        price: 19,
        quantity: 1,
        coupon: "MAY_DEALS_3",
        position: 1,
        url: "https://www.example.com/product/path",
        imageUrl: "https://www.example.com/product/path.jpg"
    )
    
    let dummyPromotion = ECommercePromotion(promotionId: "promo_1", creative: "top_banner_2", name: "75% store-wide shoe sale", position: "home_banner_top")
    
    private func createCart(builder: ECommercePropertyBuilder) {
        
    }
    
    private func createOrder(builder: ECommercePropertyBuilder) throws -> ECommercePropertyBuilder {
        return try builder.createOrder(orderId: "50314b8e9bcf000000000000", affiliation: "Google Store", total: 0, value: 30, revenue: 25, shippingCost: 3, tax: 2, discount: 2.5, coupon: "hasbros", currency: "USD").addProductsToOrder(products: dummyProduct, dummyProduct, dummyProduct)
    }
    
    private func updateOrder(builder: ECommercePropertyBuilder) {
        
    }
    
    /*PRODUCTS_SEARCHED*/
    func testProductSearch() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCTS_SEARCHED.getValue())
                .setProperty(property: try ECommercePropertyBuilder().addQuery(query: "blue hotpants").buildQuery())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
    /*PRODUCT_LIST_VIEWED*/
    func testProductListViewed() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCT_LIST_VIEWED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .addProductView(product: dummyProduct, dummyProduct, dummyProduct)
                    .buildProductViewProperty(listId: "hot_deals_1", category: "Deals"))
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
    /*PRODUCT_LIST_FILTERED*/
    func testProductListFiltered() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCT_LIST_FILTERED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .addProductFilter(product: dummyProduct, dummyProduct, dummyProduct)
                    .addFilter(filter: TypeValuePair(type: "department", value: "beauty"), TypeValuePair(type: "price", value: "under-$25"))
                    .addSort(sort: TypeValuePair(type: "price", value: "desc"))
                    .buildProuctFilterProperty(listId: "todays_deals_may_11_2016", category: "Deals"))
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
    /*PROMOTION_VIEWED*/
    func testPromotionViewed() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PROMOTION_VIEWED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .addPromotionView(promotion: dummyPromotion)
                    .buildPromotionView())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    PROMOTION_CLICKED
    func testPromotionClicked() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PROMOTION_CLICKED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .addPromotionClick(promotion: dummyPromotion)
                    .buildPromotionClick())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    PRODUCT_CLICKED
    func testProductClicked() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCT_CLICKED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .addProductClicked(product: dummyProduct)
                    .buildProductClick())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    PRODUCT_VIEWED
    func testProductViewed() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCT_VIEWED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .addProductViewed(product: dummyProduct)
                    .buildProductViewed())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    PRODUCT_ADDED
    func testProductAdded() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCT_ADDED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .addProductToCart(product: dummyProduct)
                    .buildProductAddedToCart(cartId: "skdjsidjsdkdj29j"))
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    PRODUCT_REMOVED
    func testProductRemoved() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCT_REMOVED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .removeProductFromCart(product: dummyProduct)
                    .buildProductRemovedFromCart(cartId: "skdjsidjsdkdj29j"))
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    CART_VIEWED
    func testCartViewed() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.CART_VIEWED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .createCart(cartId: "skdjsidjsdkdj29j")
                    .addProductsToCart(products: dummyProduct, dummyProduct, dummyProduct)
                    .buildForCartView())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    CHECKOUT_STARTED
    func testCheckoutStarted() {
        do {
            var builder = ECommercePropertyBuilder()
            builder = try createOrder(builder: builder)
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.CHECKOUT_STARTED.getValue())
                .setProperty(property: try builder.buildForOrderProperty())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    CHECKOUT_STEP_VIEWED
    func testCheckoutStepViewed() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.CHECKOUT_STEP_VIEWED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForCheckoutProperty(step: 2, shippingMethod: "Fedex", paymentMethod: "Visa", checkoutId: "50314b8e9bcf000000000000", orderId: "50314b8e9bcf000000000000"))
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    CHECKOUT_STEP_COMPLETED
    func testCheckoutStepCompleted() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.CHECKOUT_STEP_COMPLETED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForCheckoutProperty(step: 2, shippingMethod: "Fedex", paymentMethod: "Visa", checkoutId: "50314b8e9bcf000000000000", orderId: "50314b8e9bcf000000000000"))
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    PAYMENT_INFO_ENTERED
    func testPaymentInfoEntered() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.PAYMENT_INFO_ENTERED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForCheckoutProperty(step: 2, shippingMethod: "Fedex", paymentMethod: "Visa", checkoutId: "50314b8e9bcf000000000000", orderId: "50314b8e9bcf000000000000"))
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    ORDER_UPDATED
    func testOrderUpdated() {
        do {
            var builder = ECommercePropertyBuilder()
            builder = try createOrder(builder: builder)
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.ORDER_UPDATED.getValue())
                .setProperty(property: try builder.buildForOrderProperty())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    ORDER_COMPLETED
    func testOrderCompleted()  {
        do {
            var builder = ECommercePropertyBuilder()
            builder = try createOrder(builder: builder)
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.ORDER_COMPLETED.getValue())
                .setProperty(property: try builder.buildForOrderProperty())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    ORDER_REFUNDED ==> FULL
    func testOrderRefundedFull() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.ORDER_REFUNDED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForFullRefund( orderId: "50314b8e9bcf000000000000"))
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    ORDER_REFUNDED ==> PARTIAL
    func testOrderRefundedPartial() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.ORDER_REFUNDED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForPartialRefund(orderId: "50314b8e9bcf000000000000", total: 30, currency: "USD", products: dummyProduct, dummyProduct)
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    ORDER_CANCELLED
    func testOrderCancelled()  {
        do {
            var builder = ECommercePropertyBuilder()
            builder = try createOrder(builder: builder)
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.ORDER_CANCELLED.getValue())
                .setProperty(property: try builder.buildForOrderProperty())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    COUPON_ENTERED
    func testCouponEntered() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.COUPON_ENTERED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForCouponProperty(couponName: "May Deals", discount: 2, cartId: "skdjsidjsdkdj29j", orderId: "50314b8e9bcf000000000000", couponId: "may_deals_2016")
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    COUPON_APPLIED
    func testCouponApplied() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.COUPON_APPLIED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForCouponProperty(couponName: "May Deals", discount: 2, cartId: "skdjsidjsdkdj29j", orderId: "50314b8e9bcf000000000000", couponId: "may_deals_2016")
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    COUPON_DENIED
    func testCouponDenied() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.COUPON_DENIED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForCouponProperty(couponName: "May Deals 2016", discount: 2, reason: "expired", cartId: "skdjsidjsdkdj29j", orderId: "50314b8e9bcf000000000000", couponId: "may_deals_2016")
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    COUPON_REMOVED
    func testCouponRemoved()  {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.COUPON_REMOVED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForCouponProperty(couponName: "May Deals", discount: 2, cartId: "skdjsidjsdkdj29j", orderId: "50314b8e9bcf000000000000", couponId: "may_deals_2016")
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    PRODUCT_ADDED_TO_WISH_LIST
    func testProductAddedToWishList() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.PRODUCT_ADDED_TO_WISH_LIST.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForWishList(wishListId: "skdjsidjsdkdj29j", wishListName: "Loved Games", product: dummyProduct)
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    PRODUCT_REMOVED_FROM_WISH_LIST
    func testProductRemovedFromWishList() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.PRODUCT_REMOVED_FROM_WISH_LIST.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForWishList(wishListId: "skdjsidjsdkdj29j", wishListName: "Loved Games", product: dummyProduct)
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    WISH_LIST_PRODUCT_ADDED_TO_CART
    func testWishListProductAddedToCart() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.WISH_LIST_PRODUCT_ADDED_TO_CART.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForWishListToCart(wishListId: "skdjsidjsdkdj29j", wishListName: "Loved Games", cartId: "skdjsidjsdkdj29j", product: dummyProduct)
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    PRODUCT_SHARED
    func testProductShared() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.PRODUCT_SHARED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForProductShared(shareVia: "email", shareMessage: "Hey, check out this item", recipient: "friend@gmail.com", product: dummyProduct)
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
    
//    CART_SHARED
    func testCartShared()  {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.CART_SHARED.getValue())
                .setProperty(property: try ECommercePropertyBuilder()
                    .createCart(cartId: "skdjsidjsdkdj29j")
                    .addProductsToCart(products: dummyProduct, dummyProduct, dummyProduct)
                    .buildForCartShared(shareVia: "email", shareMessage: "Hey, check out this item", recipient: "friend@gmail.com"))
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
    
//    PRODUCT_REVIEWED
    func testProductReviewed() {
        let rudderEvent = RudderEventBuilder()
            .setChannel(channel: "Test Channel")
            .setEvent(event: ECommerceEvents.PRODUCT_REVIEWED.getValue())
            .setProperty(property: ECommercePropertyBuilder()
                .buildForProductReview(productId: dummyProduct.productId, reviewId: "kdfjrj39fj39jf3", reviewBody: "I love this product", rating: "5")
            )
            .build()
        rudderClient.track(event: rudderEvent)
        rudderClient.flush()
        sleep(2)
    }
}
