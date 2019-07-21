//
//  ECommerceCart.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class ECommerceCart: Encodable {
    var cartId: String = ""
    var products: [ECommerceProduct] = []
    
    func addProduct(product: ECommerceProduct) {
        products.append(product)
    }
    
    func addProducts(products: [ECommerceProduct]) {
        self.products.append(contentsOf: products)
    }
    
    func removeProduct(product: ECommerceProduct) {
        self.products.removeAll { (p) -> Bool in
            p.productId == product.productId
        }
    }
    
    enum CodingKeys: String, CodingKey {
        case cartId = "cart_id"
        case products
    }
}
