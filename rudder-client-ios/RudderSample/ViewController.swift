//
//  ViewController.swift
//  RudderSample
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    // MARK: Properties
    @IBOutlet weak var postBtn: UIButton!
    @IBOutlet weak var logBtn: UIButton!
    @IBOutlet weak var appNameLabel: UILabel!
    
    let appDelegate = UIApplication.shared.delegate as! AppDelegate
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        do {
            //            let dummyProduct = ECommerceProduct(
            //                productId: "507f1f77bcf86cd799439011",
            //                sku: "45790-32",
            //                category: "Games",
            //                name: "Monopoly: 3rd Edition",
            //                brand: "Monopoly",
            //                variant: "Single User",
            //                price: 19,
            //                quantity: 1,
            //                coupon: "MAY_DEALS_3",
            //                position: 1,
            //                url: "https://www.example.com/product/path",
            //                imageUrl: "https://www.example.com/product/path.jpg"
            //            )
            
            //            var property: RudderProperty = try RudderPropertyBuilder().build()
            //            appDelegate.rudderClient?.track(event: RudderEventBuilder()
            //                .setChannel(channel: "Test Channel")
            //                .setEvent(event: ECommerceEvents.PRODUCTS_SEARCHED.getValue())
            //                .setProperty(property: try ECommercePropertyBuilder().addQuery(query: "blue hotpants").buildQuery())
            //                .build()
            //            )
            
            //          PRODUCTS_SEARCHED
            appDelegate.rudderClient?.track(event: RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCTS_SEARCHED.getValue())
                .setProperty(property: try ECommercePropertyBuilder().addQuery(query: "blue hotpants").buildQuery())
                .build()
            )
            
            //          PRODUCT_LIST_VIEWED
            //            appDelegate.rudderClient?.track(event: RudderEventBuilder()
            //                .setChannel(channel: "Test Channel")
            //                .setEvent(event: ECommerceEvents.PRODUCT_LIST_VIEWED.getValue())
            //                .setProperty(property: try ECommercePropertyBuilder()
            //                    .addProductView(product: dummyProduct)
            //                    .addProductView(product: dummyProduct)
            //                    .addProductView(product: dummyProduct)
            //                    .buildProductViewProperty(listId: "hot_deals_1", category: "Deals"))
            //                .build()
            //            )
            
            //            PRODUCT_LIST_FILTERED
            //            appDelegate.rudderClient?.track(event: RudderEventBuilder()
            //                .setChannel(channel: "Test Channel")
            //                .setEvent(event: ECommerceEvents.PRODUCT_LIST_VIEWED.getValue())
            //                .setProperty(property: try ECommercePropertyBuilder()
            //                    .addProductFilter(product: dummyProduct)
            //                    .addProductFilter(product: dummyProduct)
            //                    .addProductFilter(product: dummyProduct)
            //                    .addFilter(TypeValuePair(type: "department", value: "beauty"))
            //                    .addFilter(TypeValuePair(type: "price", value: "under-$25"))
            //                    .addSort(TypeValuePair(type: "price", value: "desc"))
            //                    .buildProductFilterProperty(listId: "todays_deals_may_11_2016", category: "Deals"))
            //                .build()
            //            )
        } catch {
            print("ERROR: " + error.localizedDescription)
        }
        appDelegate.rudderClient?.flush()
    }

    // MARK: Actions
    @IBAction func OnPostEvent(_ sender: Any, forEvent event: UIEvent) {
        appNameLabel.text = "Post Event Button clicked"
        do {
//            let dummyProduct = ECommerceProduct(
//                productId: "507f1f77bcf86cd799439011",
//                sku: "45790-32",
//                category: "Games",
//                name: "Monopoly: 3rd Edition",
//                brand: "Monopoly",
//                variant: "Single User",
//                price: 19,
//                quantity: 1,
//                coupon: "MAY_DEALS_3",
//                position: 1,
//                url: "https://www.example.com/product/path",
//                imageUrl: "https://www.example.com/product/path.jpg"
//            )
            
//            var property: RudderProperty = try RudderPropertyBuilder().build()
//            appDelegate.rudderClient?.track(event: RudderEventBuilder()
//                .setChannel(channel: "Test Channel")
//                .setEvent(event: ECommerceEvents.PRODUCTS_SEARCHED.getValue())
//                .setProperty(property: try ECommercePropertyBuilder().addQuery(query: "blue hotpants").buildQuery())
//                .build()
//            )
            
//          PRODUCTS_SEARCHED
            appDelegate.rudderClient?.track(event: RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: ECommerceEvents.PRODUCTS_SEARCHED.getValue())
                .setProperty(property: try ECommercePropertyBuilder().addQuery(query: "blue hotpants").buildQuery())
                .build()
            )
            
//          PRODUCT_LIST_VIEWED
//            appDelegate.rudderClient?.track(event: RudderEventBuilder()
//                .setChannel(channel: "Test Channel")
//                .setEvent(event: ECommerceEvents.PRODUCT_LIST_VIEWED.getValue())
//                .setProperty(property: try ECommercePropertyBuilder()
//                    .addProductView(product: dummyProduct)
//                    .addProductView(product: dummyProduct)
//                    .addProductView(product: dummyProduct)
//                    .buildProductViewProperty(listId: "hot_deals_1", category: "Deals"))
//                .build()
//            )
            
//            PRODUCT_LIST_FILTERED
//            appDelegate.rudderClient?.track(event: RudderEventBuilder()
//                .setChannel(channel: "Test Channel")
//                .setEvent(event: ECommerceEvents.PRODUCT_LIST_VIEWED.getValue())
//                .setProperty(property: try ECommercePropertyBuilder()
//                    .addProductFilter(product: dummyProduct)
//                    .addProductFilter(product: dummyProduct)
//                    .addProductFilter(product: dummyProduct)
//                    .addFilter(TypeValuePair(type: "department", value: "beauty"))
//                    .addFilter(TypeValuePair(type: "price", value: "under-$25"))
//                    .addSort(TypeValuePair(type: "price", value: "desc"))
//                    .buildProductFilterProperty(listId: "todays_deals_may_11_2016", category: "Deals"))
//                .build()
//            )
        } catch {
            print("ERROR: " + error.localizedDescription)
        }
        appDelegate.rudderClient?.flush()
    }
    
    @IBAction func onLogEvent(_ sender: Any, forEvent event: UIEvent) {
        appNameLabel.text = "Log Event button clicked"
        appDelegate.rudderClient?.flush()
    }
    
}
