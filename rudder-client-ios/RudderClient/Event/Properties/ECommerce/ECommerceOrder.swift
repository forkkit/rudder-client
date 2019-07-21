//
//  ECommerceOrder.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct ECommerceOrder : Encodable {
    var orderId: String = ""
    var affiliation: String = ""
    var total: Float = 0
    var value: Float = 0
    var revenue: Float = 0
    var shippingCost: Float = 0
    var tax: Float = 0
    var discount: Float = 0
    var coupon: String = ""
    var currency: String = ""
    var products: [ECommerceProduct] = []
    
    enum CodingKeys: String, CodingKey {
        case orderId = "order_id"
        case shippingCost = "shipping"
        case affiliation, total, value, revenue, tax, discount, coupon, currency, products
    }
}
