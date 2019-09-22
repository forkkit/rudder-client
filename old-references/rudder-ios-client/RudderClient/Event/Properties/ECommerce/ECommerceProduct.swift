//
//  ECommerceProduct.swift
//  RudderSample
//
//  Created by Arnab Pal on 16/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct ECommerceProduct: Encodable {
    var productId: String = ""
    var sku: String = ""
    var category = ""
    var name: String = ""
    var brand: String = ""
    var variant: String = ""
    var price: Float = 0
    var quantity: Float = 0
    var coupon: String = ""
    var position: Int = 0
    var url: String = ""
    var imageUrl: String = ""
    
    enum CodingKeys: String, CodingKey {
        case productId = "product_id"
        case imageUrl = "image_url"
        case sku, name, brand, variant, price, quantity, coupon, position, url, category
    }
}

extension ECommerceProduct {
    func toProperty() -> PropertyObject {
        return PropertyObject.object([
            CodingKeys.productId.rawValue: PropertyObject.string(productId),
            CodingKeys.sku.rawValue: PropertyObject.string(sku),
            CodingKeys.category.rawValue: PropertyObject.string(category),
            CodingKeys.name.rawValue: PropertyObject.string(name),
            CodingKeys.brand.rawValue: PropertyObject.string(brand),
            CodingKeys.variant.rawValue: PropertyObject.string(variant),
            CodingKeys.price.rawValue: PropertyObject.float(price),
            CodingKeys.quantity.rawValue: PropertyObject.float(quantity),
            CodingKeys.coupon.rawValue: PropertyObject.string(coupon),
            CodingKeys.position.rawValue: PropertyObject.int(position),
            CodingKeys.url.rawValue: PropertyObject.string(url),
            CodingKeys.imageUrl.rawValue: PropertyObject.string(imageUrl)
        ])
    }
}

extension ECommerceProduct {
    func addProductToProperty(property: RudderProperty) {
        property.addStringProperty(key: CodingKeys.productId.rawValue, value: self.productId)
        property.addStringProperty(key: CodingKeys.sku.rawValue, value: self.sku)
        property.addStringProperty(key: CodingKeys.category.rawValue, value: self.category)
        property.addStringProperty(key: CodingKeys.name.rawValue, value: self.name)
        property.addStringProperty(key: CodingKeys.brand.rawValue, value: self.brand)
        property.addStringProperty(key: CodingKeys.variant.rawValue, value: self.variant)
        property.addFloatProperty(key: CodingKeys.price.rawValue, value: self.price)
        property.addFloatProperty(key: CodingKeys.quantity.rawValue, value: self.quantity)
        property.addStringProperty(key: CodingKeys.coupon.rawValue, value: self.coupon)
        property.addIntProperty(key: CodingKeys.position.rawValue, value: self.position)
        property.addStringProperty(key: CodingKeys.url.rawValue, value: self.url)
        property.addStringProperty(key: CodingKeys.imageUrl.rawValue, value: self.imageUrl)
    }
}
