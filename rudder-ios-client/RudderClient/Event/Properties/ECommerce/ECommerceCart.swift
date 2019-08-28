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
    
    func setCartId(cartId: String) -> ECommerceCart {
        self.cartId = cartId
        return self
    }
    
    func addProduct(product: ECommerceProduct) -> ECommerceCart {
        products.append(product)
        return self
    }
    
    func addProducts(products: [ECommerceProduct]) -> ECommerceCart {
        self.products.append(contentsOf: products)
        return self
    }
    
    func removeProduct(product: ECommerceProduct) -> ECommerceCart {
        self.products.removeAll { (p) -> Bool in
            p.productId == product.productId
        }
        return self
    }
    
    enum CodingKeys: String, CodingKey {
        case cartId = "cart_id"
        case products
    }
}

extension ECommerceCart {
    func addToProperty(property: RudderProperty) {
        var productArr : [PropertyObject] = []
        for p in self.products {
            productArr.append(p.toProperty())
        }
        property.addStringProperty(key: CodingKeys.cartId.rawValue, value: self.cartId)
        property.addListProperty(key: CodingKeys.products.rawValue, value: productArr)
    }
}
