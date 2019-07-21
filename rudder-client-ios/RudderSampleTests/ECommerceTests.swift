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
    
    private func createCart(builder: ECommerceCartBuilder) {
        
    }
    
    private func createOrder(builder: ECommerceCartBuilder) {
        
    }
    
    private func updateOrder(builder: ECommerceCartBuilder) {
        
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
        } catch {
            printError(_error: error)
        }
    }
}
