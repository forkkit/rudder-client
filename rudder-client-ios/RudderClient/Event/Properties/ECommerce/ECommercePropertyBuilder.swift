//
//  ECommercePropertyBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class ECommercePropertyBuilder {
//    PRODUCTS_SEARCHED
    private var query: String? = nil
    func addQuery(query: String) -> ECommercePropertyBuilder {
        self.query = query
        return self
    }
    
    func buildQuery() throws -> RudderProperty {
        if (query == nil) {
            throw InvalidStateError(_message: "Search Query is not set")
        }
        
        let property = RudderProperty()
        property.addStringProperty(key: "query", value: query ?? "")
        return property
    }
    
//    PRODUCT_LIST_VIEWED
    private var producViewedtList: [ECommerceProduct]? = nil
    func addProductView(product: ECommerceProduct...) -> ECommercePropertyBuilder {
        if (self.producViewedtList == nil) {
            self.producViewedtList = []
        }
        self.producViewedtList!.append(contentsOf: product)
        return self
    }
    
    func buildProductViewProperty(listId: String, category: String) throws -> RudderProperty {
        if (producViewedtList == nil || producViewedtList?.count == 0) {
            throw InvalidStateError(_message: "No product has been added")
        }
        let property = RudderProperty()
        var productArr: [PropertyObject] = []
        for product in producViewedtList! { productArr.append(product.toProperty()) }
        property.addStringProperty(key: "list_id", value: listId)
        property.addStringProperty(key: "category", value: category)
        property.addListProperty(key: "products", value: productArr)
        // reset the list
        producViewedtList = nil
        return property
    }
    
//    PRODUCT_LIST_FILTERED
    private var productFilteredList: [ECommerceProduct]? = nil
    func addProductFilter(product: ECommerceProduct...) -> ECommercePropertyBuilder {
        if (self.productFilteredList == nil) {
            self.productFilteredList = []
        }
        self.productFilteredList!.append(contentsOf: product)
        return self
    }
    
    private var filterList: [TypeValuePair]? = nil
    func addFilter(filter: TypeValuePair...) -> ECommercePropertyBuilder {
        if (self.filterList == nil) {
            self.filterList = []
        }
        self.filterList?.append(contentsOf: filter)
        return self
    }
    
    private var sortList: [TypeValuePair]? = nil
    func addSort(sort: TypeValuePair...) -> ECommercePropertyBuilder {
        if (self.sortList == nil) {
            self.sortList = []
        }
        self.sortList?.append(contentsOf: sort)
        return self
    }
    
    func buildProuctFilterProperty(listId: String, category: String) throws -> RudderProperty {
        if (self.productFilteredList == nil || self.productFilteredList?.count == 0) {
            throw InvalidStateError(_message: "No product has been added")
        }
        let property = RudderProperty()
        
        var productArr: [PropertyObject] = []
        for product in productFilteredList! {
            productArr.append(product.toProperty())
            
        }
        var filterArr: [PropertyObject] = []
        for filter in filterList! {
            filterArr.append(filter.toProperty())
        }
        var sortArr: [PropertyObject] = []
        for sort in sortList! {
            sortArr.append(sort.toProperty())
        }
        property.addStringProperty(key: "list_id", value: listId)
        property.addStringProperty(key: "category", value: category)
        property.addListProperty(key: "products", value: productArr)
        property.addListProperty(key: "filters", value: filterArr)
        property.addListProperty(key: "sorts", value: sortArr)
        return property
    }
    
//    PROMOTION_VIEWED
    private var promotionViewed: ECommercePromotion? = nil
    func addPromotionView(promotion: ECommercePromotion) -> ECommercePropertyBuilder {
        self.promotionViewed = promotion
        return self
    }
    
    func buildPromotionView() throws -> RudderProperty {
        if (self.promotionViewed == nil) {
            throw InvalidStateError(_message: "Promotion is not added")
        }
        
        let property = RudderProperty()
        property.addStringProperty(key: "promotion_id", value: self.promotionViewed!.promotionId)
        property.addStringProperty(key: "creative", value: self.promotionViewed!.creative)
        property.addStringProperty(key: "name", value: self.promotionViewed!.name)
        property.addStringProperty(key: "position", value: self.promotionViewed!.position)
        return property
    }
    
//    PROMOTION_CLICKED
    private var promotionClicked: ECommercePromotion? = nil
    func addPromotionClick(promotion: ECommercePromotion) -> ECommercePropertyBuilder {
        self.promotionClicked = promotion
        return self
    }
    
    func buildPromotionClick() throws -> RudderProperty {
        if (self.promotionClicked == nil) {
            throw InvalidStateError(_message: "Promotion is not added")
        }
        
        let property = RudderProperty()
        property.addStringProperty(key: "promotion_id", value: self.promotionClicked!.promotionId)
        property.addStringProperty(key: "creative", value: self.promotionClicked!.creative)
        property.addStringProperty(key: "name", value: self.promotionClicked!.name)
        property.addStringProperty(key: "position", value: self.promotionClicked!.position)
        return property
    }
 }
