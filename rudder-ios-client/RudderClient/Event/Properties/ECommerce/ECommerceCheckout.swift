//
//  ECommerceCheckout.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct ECommerceCheckout: Encodable {
    var step: Int = 0
    var shippingMethod: String = ""
    var paymentMethod: String = ""
    
    enum CodingKeys: String, CodingKey {
        case shippingMethod = "shipping_method"
        case paymentMethod = "payment_method"
        case step
    }
}
