//
//  ECommercePromotion.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct ECommercePromotion: Encodable {
    var promotionId: String = ""
    var creative: String = ""
    var name: String = ""
    var position: String = ""
    
    enum CodingKeys: String, CodingKey {
        case promotionId = "promotion_id"
        case creative, name, position
    }
}
