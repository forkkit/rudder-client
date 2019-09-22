//
//  ECommerceProductBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class ECommerceProductBuilder {
    private var productId: String = ""
    func setProductId(productId: String) -> ECommerceProductBuilder{
        self.productId = productId
        return self
    }
    
    private var sku: String = ""
    func setSku(sku: String) -> ECommerceProductBuilder {
        self.sku = sku
        return self
    }
    
    private var category: String = ""
    func setCategory(category: String) -> ECommerceProductBuilder {
        self.category = category
        return self
    }
    
    private var name: String = ""
    func setName(name: String) -> ECommerceProductBuilder {
        self.name = name
        return self
    }
    
    private var brand: String = ""
    func setBrand(brand: String) -> ECommerceProductBuilder {
        self.brand = brand
        return self
    }
    
    private var variant : String = ""
    func setVariant(variant: String) -> ECommerceProductBuilder {
        self.variant = variant
        return self
    }
    
    private var price: Float = 0
    func setPrice(price: Float) -> ECommerceProductBuilder {
        self.price = price
        return self
    }
    
    private var quantity: Float = 0
    func setQuantity(quantity: Float) -> ECommerceProductBuilder {
        self.quantity = quantity
        return self
    }
    
    private var coupon: String = ""
    func setCoupon(coupon: String) -> ECommerceProductBuilder {
        self.coupon = coupon
        return self
    }
    
    private var position: Int = 0
    func setPosition(position: Int) -> ECommerceProductBuilder {
        self.position = position
        return self
    }
    
    private var url: String = ""
    func setUrl(url: String) -> ECommerceProductBuilder {
        self.url = url
        return self
    }
    
    private var imageUrl: String = ""
    func setImageUrl(imageUrl: String) -> ECommerceProductBuilder {
        self.imageUrl = imageUrl
        return self
    }
    
    func build() -> ECommerceProduct {
        return ECommerceProduct(productId: productId, sku: sku, category: category, name: name, brand: brand, variant: variant, price: price, quantity: quantity, coupon: coupon, position: position, url: url, imageUrl: imageUrl)
    }
}
