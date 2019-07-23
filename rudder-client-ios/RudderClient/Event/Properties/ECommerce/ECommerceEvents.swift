//
//  ECommerceEvents.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

enum ECommerceEvents : Encodable {
    case PRODUCTS_SEARCHED, PRODUCT_LIST_VIEWED, PRODUCT_LIST_FILTERED, PROMOTION_VIEWED, PROMOTION_CLICKED, PRODUCT_CLICKED, PRODUCT_VIEWED, PRODUCT_ADDED, PRODUCT_REMOVED, CART_VIEWED, CHECKOUT_STARTED, CHECKOUT_STEP_VIEWED, CHECKOUT_STEP_COMPLETED, PAYMENT_INFO_ENTERED, ORDER_UPDATED, ORDER_COMPLETED, ORDER_REFUNDED, ORDER_CANCELLED, COUPON_ENTERED, COUPON_APPLIED, COUPON_DENIED, COUPON_REMOVED, PRODUCT_ADDED_TO_WISH_LIST, PRODUCT_REMOVED_FROM_WISH_LIST, WISH_LIST_PRODUCT_ADDED_TO_CART, PRODUCT_SHARED, CART_SHARED, PRODUCT_REVIEWED
    
    public func getValue() -> String {
        switch self {
        case .PRODUCTS_SEARCHED:
            return "Products Searched"
        case .PRODUCT_LIST_VIEWED:
            return "Product List Viewed"
        case .PRODUCT_LIST_FILTERED:
            return "Product List Filtered"
        case .PROMOTION_VIEWED:
            return "Promotion Viewed"
        case .PROMOTION_CLICKED:
            return "Promotion Clicked"
        case .PRODUCT_CLICKED:
            return "Product Clicked"
        case .PRODUCT_VIEWED:
            return "Product Viewed"
        case .PRODUCT_ADDED:
            return "Product Added"
        case .PRODUCT_REMOVED:
            return "Product Removed"
        case .CART_VIEWED:
            return "Cart Viewed"
        case .CHECKOUT_STARTED:
            return "Checkout Started"
        case .CHECKOUT_STEP_VIEWED:
            return "Checkout Step Viewed"
        case .CHECKOUT_STEP_COMPLETED:
            return "Checkout Step Completed"
        case .PAYMENT_INFO_ENTERED:
            return "Payment Info Entered"
        case .ORDER_UPDATED:
            return "Order Updated"
        case .ORDER_COMPLETED:
            return "Order Completed"
        case .ORDER_REFUNDED:
            return "Order Refunded"
        case .ORDER_CANCELLED:
            return "Order Cancelled"
        case .COUPON_ENTERED:
            return "Coupon Entered"
        case .COUPON_APPLIED:
            return "Coupon Applied"
        case .COUPON_DENIED:
            return "Coupon Denied"
        case .COUPON_REMOVED:
            return "Coupon Removed"
        case .PRODUCT_ADDED_TO_WISH_LIST:
            return "Product Added to Wishlist"
        case .PRODUCT_REMOVED_FROM_WISH_LIST:
            return "Product Removed from Wishlist"
        case .WISH_LIST_PRODUCT_ADDED_TO_CART:
            return "Wishlist Product Added to Cart"
        case .PRODUCT_SHARED:
            return "Product Shared"
        case .CART_SHARED:
            return "Cart Shared"
        case .PRODUCT_REVIEWED:
            return "Product Reviewed"
        }
    }
    
    public func encode(to encoder: Encoder) throws {
        var container = encoder.singleValueContainer()
        try container.encode(getValue())
    }
}
