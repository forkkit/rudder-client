//
//  ECommerceWishlist.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct ECommerceWishlist {
    var wishListId: String = ""
    var wishListName: String = ""
    
    enum CodingKeys: String, CodingKey {
        case wishListId = "wishlist_id"
        case wishListName = "wishlist_name"
    }
}
