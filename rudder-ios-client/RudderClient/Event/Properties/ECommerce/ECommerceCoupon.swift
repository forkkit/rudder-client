//
//  ECommerceCoupon.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct ECommerceCoupon {
    var couponName: String = ""
    var discount: Float = 0
    var reason: String = ""
    
    enum CodingKeys: String, CodingKey {
        case couponName = "coupon_name"
        case discount, reason
    }
}
