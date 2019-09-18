//
//  TrackPropertyBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class TrackPropertyBuilder: RudderPropertyBuilder {
    private var category: String? = nil
    func setCategory(category: String) -> TrackPropertyBuilder {
        self.category = category
        return self
    }
    
    private var label: String = ""
    func setLabel(label: String) -> TrackPropertyBuilder {
        self.label = label
        return self
    }
    
    private var value: String = ""
    func setValue(value: String) -> TrackPropertyBuilder {
        self.value = value
        return self
    }
    
    func buildProperty() throws -> RudderProperty {
        if (self.category == nil){
            throw MalformedEventError(_message: "Key \"category\" is required for track event")
        }
        return try self.addStringProperty(key: "category", value: self.category!)
        .addStringProperty(key: "label", value: self.label)
        .addStringProperty(key: "value", value: self.value)
        .build()
    }
}
