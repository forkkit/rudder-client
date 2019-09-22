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
    
    mutating func addProducts(products: [ECommerceProduct]) {
        self.products.append(contentsOf: products)
    }
    
    func addToProperty(property: RudderProperty) {
        property.addStringProperty(key: CodingKeys.orderId.rawValue, value: self.orderId)
        property.addStringProperty(key: CodingKeys.affiliation.rawValue, value: self.affiliation)
        property.addFloatProperty(key: CodingKeys.total.rawValue, value: self.total)
        property.addFloatProperty(key: CodingKeys.value.rawValue, value: self.value)
        property.addFloatProperty(key: CodingKeys.revenue.rawValue, value: self.revenue)
        property.addFloatProperty(key: CodingKeys.shippingCost.rawValue, value: self.shippingCost)
        property.addFloatProperty(key: CodingKeys.tax.rawValue, value: self.tax)
        property.addFloatProperty(key: CodingKeys.discount.rawValue, value: self.discount)
        property.addStringProperty(key: CodingKeys.coupon.rawValue, value: self.coupon)
        property.addStringProperty(key: CodingKeys.currency.rawValue, value: self.currency)
        
        var productArr: [PropertyObject] = []
        for product in self.products {
            productArr.append(product.toProperty())
        }
        property.addListProperty(key: CodingKeys.products.rawValue, value: productArr)
    }
}
