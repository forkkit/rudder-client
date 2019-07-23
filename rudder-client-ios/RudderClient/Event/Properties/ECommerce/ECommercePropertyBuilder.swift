//
//  ECommercePropertyBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class ECommercePropertyBuilder {
//    PRODUCTS_SEARCHED
    private var query: String? = nil
    func addQuery(query: String) -> ECommercePropertyBuilder {
        self.query = query
        return self
    }
    
    func buildQuery() throws -> RudderProperty {
        if (query == nil) {
            throw InvalidStateError(_message: "Search Query is not set")
        }
        
        let property = RudderProperty()
        property.addStringProperty(key: "query", value: query ?? "")
        return property
    }
    
//    PRODUCT_LIST_VIEWED
    private var producViewedtList: [ECommerceProduct]? = nil
    func addProductView(product: ECommerceProduct...) -> ECommercePropertyBuilder {
        if (self.producViewedtList == nil) {
            self.producViewedtList = []
        }
        self.producViewedtList!.append(contentsOf: product)
        return self
    }
    
    func buildProductViewProperty(listId: String, category: String) throws -> RudderProperty {
        if (producViewedtList == nil || producViewedtList?.count == 0) {
            throw InvalidStateError(_message: "No product has been added")
        }
        let property = RudderProperty()
        var productArr: [PropertyObject] = []
        for product in producViewedtList! { productArr.append(product.toProperty()) }
        property.addStringProperty(key: "list_id", value: listId)
        property.addStringProperty(key: "category", value: category)
        property.addListProperty(key: "products", value: productArr)
        // reset the list
        producViewedtList = nil
        return property
    }
    
//    PRODUCT_LIST_FILTERED
    private var productFilteredList: [ECommerceProduct]? = nil
    func addProductFilter(product: ECommerceProduct...) -> ECommercePropertyBuilder {
        if (self.productFilteredList == nil) {
            self.productFilteredList = []
        }
        self.productFilteredList!.append(contentsOf: product)
        return self
    }
    
    private var filterList: [TypeValuePair]? = nil
    func addFilter(filter: TypeValuePair...) -> ECommercePropertyBuilder {
        if (self.filterList == nil) {
            self.filterList = []
        }
        self.filterList?.append(contentsOf: filter)
        return self
    }
    
    private var sortList: [TypeValuePair]? = nil
    func addSort(sort: TypeValuePair...) -> ECommercePropertyBuilder {
        if (self.sortList == nil) {
            self.sortList = []
        }
        self.sortList?.append(contentsOf: sort)
        return self
    }
    
    func buildProuctFilterProperty(listId: String, category: String) throws -> RudderProperty {
        if (self.productFilteredList == nil || self.productFilteredList?.count == 0) {
            throw InvalidStateError(_message: "No product has been added")
        }
        let property = RudderProperty()
        
        var productArr: [PropertyObject] = []
        for product in productFilteredList! {
            productArr.append(product.toProperty())
            
        }
        var filterArr: [PropertyObject] = []
        for filter in filterList! {
            filterArr.append(filter.toProperty())
        }
        var sortArr: [PropertyObject] = []
        for sort in sortList! {
            sortArr.append(sort.toProperty())
        }
        property.addStringProperty(key: "list_id", value: listId)
        property.addStringProperty(key: "category", value: category)
        property.addListProperty(key: "products", value: productArr)
        property.addListProperty(key: "filters", value: filterArr)
        property.addListProperty(key: "sorts", value: sortArr)
        return property
    }
    
//    PROMOTION_VIEWED
    private var promotionViewed: ECommercePromotion? = nil
    func addPromotionView(promotion: ECommercePromotion) -> ECommercePropertyBuilder {
        self.promotionViewed = promotion
        return self
    }
    
    func buildPromotionView() throws -> RudderProperty {
        if (self.promotionViewed == nil) {
            throw InvalidStateError(_message: "Promotion is not added")
        }
        
        let property = RudderProperty()
        property.addStringProperty(key: "promotion_id", value: self.promotionViewed!.promotionId)
        property.addStringProperty(key: "creative", value: self.promotionViewed!.creative)
        property.addStringProperty(key: "name", value: self.promotionViewed!.name)
        property.addStringProperty(key: "position", value: self.promotionViewed!.position)
        return property
    }
    
//    PROMOTION_CLICKED
    private var promotionClicked: ECommercePromotion? = nil
    func addPromotionClick(promotion: ECommercePromotion) -> ECommercePropertyBuilder {
        self.promotionClicked = promotion
        return self
    }
    
    func buildPromotionClick() throws -> RudderProperty {
        if (self.promotionClicked == nil) {
            throw InvalidStateError(_message: "Promotion is not added")
        }
        
        let property = RudderProperty()
        property.addStringProperty(key: "promotion_id", value: self.promotionClicked!.promotionId)
        property.addStringProperty(key: "creative", value: self.promotionClicked!.creative)
        property.addStringProperty(key: "name", value: self.promotionClicked!.name)
        property.addStringProperty(key: "position", value: self.promotionClicked!.position)
        return property
    }
    
//    PRODUCT_CLICKED
    private var productClicked: ECommerceProduct? = nil
    func addProductClicked(product: ECommerceProduct) -> ECommercePropertyBuilder {
        self.productClicked = product
        return self
    }
    
    func buildProductClick() throws -> RudderProperty {
        if (productClicked == nil) {
            throw InvalidStateError(_message: "Product is not added")
        }
        let property = RudderProperty()
        productClicked?.addProductToProperty(property: property)
        return property
    }
    
//    PRODUCT_VIEWED
    private var productViewed: ECommerceProduct? = nil
    func addProductViewed(product: ECommerceProduct) -> ECommercePropertyBuilder {
        self.productViewed = product
        return self
    }
    
    func buildProductViewed() throws -> RudderProperty {
        if (productViewed == nil) {
            throw InvalidStateError(_message: "Product is not added")
        }
        let property = RudderProperty()
        productViewed?.addProductToProperty(property: property)
        return property
    }
    
//    PRODUCT_ADDED
    private var productAddedToCart: ECommerceProduct? = nil
    func addProductToCart(product: ECommerceProduct) -> ECommercePropertyBuilder {
        self.productAddedToCart = product
        return self
    }
    
    func buildProductAddedToCart(cartId: String) throws -> RudderProperty {
        if (productAddedToCart == nil) {
           throw InvalidStateError(_message: "Product is not added")
        }
        
        let property = RudderProperty()
        productAddedToCart?.addProductToProperty(property: property)
        property.addStringProperty(key: "cart_id", value: cartId)
        return property
    }
    
//    PRODUCT_REMOVED
    private var productRemovedFromCart: ECommerceProduct? = nil
    func removeProductFromCart(product: ECommerceProduct) -> ECommercePropertyBuilder {
        self.productRemovedFromCart = product
        return self
    }
    
    func buildProductRemovedFromCart(cartId: String) throws -> RudderProperty {
        if (productRemovedFromCart == nil) {
            throw InvalidStateError(_message: "Product action not perfomed")
        }
        let property = RudderProperty()
        productRemovedFromCart?.addProductToProperty(property: property)
        property.addStringProperty(key: "cart_id", value: cartId)
        return property
    }
    
//    CART_VIEWED
    private var cartViewed: ECommerceCart? = nil
    func createCart(cartId: String) -> ECommercePropertyBuilder {
        self.cartViewed = ECommerceCart().setCartId(cartId: cartId)
        return self
    }
    
    func addProductsToCart(products: ECommerceProduct...) throws -> ECommercePropertyBuilder {
        if (cartViewed == nil) {
            throw InvalidStateError(_message: "Cart is not initialized")
        }
        self.cartViewed = self.cartViewed?.addProducts(products: products)
        return self
    }
    
    func buildForCartView() throws -> RudderProperty {
        if (self.cartViewed == nil) {
            throw InvalidStateError(_message: "Cart is not initialized")
        } else if (self.cartViewed?.products.count == 0) {
            throw InvalidStateError(_message: "Cart does not have any product")
        }
        let property = RudderProperty()
        cartViewed!.addToProperty(property: property)
        return property
    }
    
//    CHECKOUT_STARTED
    private var order: ECommerceOrder? = nil
    func createOrder(orderId: String, affiliation: String, total: Float, value: Float, revenue: Float, shippingCost: Float, tax: Float, discount: Float, coupon: String, currency: String) -> ECommercePropertyBuilder {
        self.order = ECommerceOrder(orderId: orderId, affiliation: affiliation, total: total, value: value, revenue: revenue, shippingCost: shippingCost, tax: tax, discount: discount, coupon: coupon, currency: currency, products: [])
        return self
    }
    
    func updateOrder(orderId: String, affiliation: String, total: Float, value: Float, revenue: Float, shippingCost: Float, tax: Float, discount: Float, coupon: String, currency: String) throws -> ECommercePropertyBuilder {
        if (self.order == nil) {
            throw InvalidStateError(_message: "Order is not created")
        }
        self.order = ECommerceOrder(orderId: orderId, affiliation: affiliation, total: total, value: value, revenue: revenue, shippingCost: shippingCost, tax: tax, discount: discount, coupon: coupon, currency: currency, products: self.order!.products)
        return self
    }
    
    func addProductsToOrder(products: ECommerceProduct...) throws -> ECommercePropertyBuilder {
        if (self.order == nil) {
            
        }
        self.order!.addProducts(products: products)
        return self
    }
    
//    CHECKOUT_STARTED, ORDER_UPDATED, ORDER_COMPLETED
    func buildForOrderProperty() throws -> RudderProperty {
        if (self.order == nil) {
            throw InvalidStateError(_message: "Order is not created")
        }
        
        let property = RudderProperty()
        self.order!.addToProperty(property: property)
        return property
    }
    
//    CHECKOUT_STEP_VIEWED, CHECKOUT_STEP_COMPLETED, PAYMENT_INFO_ENTERED
    func buildForCheckoutProperty(step: Int, shippingMethod: String, paymentMethod: String, checkoutId: String, orderId: String) -> RudderProperty {
        let property = RudderProperty()
        
        property.addIntProperty(key: "step", value: step)
        property.addStringProperty(key: "shipping_method", value: shippingMethod)
        property.addStringProperty(key: "payment_method", value: paymentMethod)
        property.addStringProperty(key: "checkout_id", value: checkoutId)
        property.addStringProperty(key: "order_id", value: orderId)
        
        return property
    }
    
//    ORDER_REFUNDED
    func buildForFullRefund(orderId: String) -> RudderProperty {
        let property = RudderProperty()
        
        property.addStringProperty(key: "order_id", value: orderId)
        
        return property
    }
    
    func buildForPartialRefund(orderId: String, total: Float, currency: String, products: ECommerceProduct...) -> RudderProperty {
        let property = RudderProperty()
        
        var productArr:[PropertyObject] = []
        for product in products {
            productArr.append(product.toProperty())
        }
        
        property.addStringProperty(key: "order_id", value: orderId)
        property.addFloatProperty(key: "total", value: total)
        property.addStringProperty(key: "currency", value: currency)
        property.addListProperty(key: "products", value: productArr)
        
        return property
    }
    
//    COUPON_ENTERED
    func buildForCouponProperty(couponName: String, discount: Int, reason: String =  "", cartId: String, orderId: String, couponId: String) -> RudderProperty {
        let property = RudderProperty()
        property.addStringProperty(key: "coupon_name", value: couponName)
        property.addIntProperty(key: "discount", value: discount)
        property.addStringProperty(key: "reason", value: reason)
        property.addStringProperty(key: "cart_id", value: cartId)
        property.addStringProperty(key: "order_id", value: orderId)
        property.addStringProperty(key: "coupon_id", value: couponId)
        return property
    }
    
//    PRODUCT_ADDED_TO_WISH_LIST
    func buildForWishList(wishListId: String, wishListName: String, product: ECommerceProduct) -> RudderProperty {
        let property = RudderProperty()
        product.addProductToProperty(property: property)
        property.addStringProperty(key: "wishlist_id", value: wishListId)
        property.addStringProperty(key: "wishlist_name", value: wishListName)
        return property
    }
    
//    WISH_LIST_PRODUCT_ADDED_TO_CART
    func buildForWishListToCart(wishListId: String, wishListName: String, cartId: String, product: ECommerceProduct) -> RudderProperty {
        let property = RudderProperty()
        product.addProductToProperty(property: property)
        property.addStringProperty(key: "wishlist_id", value: wishListId)
        property.addStringProperty(key: "wishlist_name", value: wishListName)
        property.addStringProperty(key: "cart_id", value: cartId)
        return property
    }
    
//    PRODUCT_SHARED
    func buildForProductShared(shareVia: String, shareMessage: String, recipient: String, product: ECommerceProduct) -> RudderProperty {
        let property = RudderProperty()
        product.addProductToProperty(property: property)
        property.addStringProperty(key: "share_via", value: shareVia)
        property.addStringProperty(key: "share_message", value: shareMessage)
        property.addStringProperty(key: "recipient", value: recipient)
        return property
    }
    
//    CART_SHARED
    func buildForCartShared(shareVia: String, shareMessage: String, recipient: String) throws -> RudderProperty {
        if (self.cartViewed == nil) {
            throw InvalidStateError(_message: "Cart is not initialized")
        } else if (self.cartViewed?.products.count == 0) {
            throw InvalidStateError(_message: "Cart does not have any product")
        }
        let property = RudderProperty()
        self.cartViewed!.addToProperty(property: property)
        property.addStringProperty(key: "share_via", value: shareVia)
        property.addStringProperty(key: "share_message", value: shareMessage)
        property.addStringProperty(key: "recipient", value: recipient)
        return property
    }
    
//    PRODUCT_REVIEWED
    func buildForProductReview(productId: String, reviewId: String, reviewBody: String, rating: String) -> RudderProperty {
        let property = RudderProperty()
        property.addStringProperty(key: "product_id", value: productId)
        property.addStringProperty(key: "review_id", value: reviewId)
        property.addStringProperty(key: "review_body", value: reviewBody)
        property.addStringProperty(key: "rating", value: rating)
        return property
    }
 }
