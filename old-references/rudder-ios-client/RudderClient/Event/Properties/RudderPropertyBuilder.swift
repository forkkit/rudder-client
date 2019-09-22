//
//  RudderPropertyBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 15/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderPropertyBuilder {
    internal var rudderProperty = RudderProperty()
    
    func addStringProperty(key: String, value: String) -> RudderPropertyBuilder {
        rudderProperty.addStringProperty(key: key, value: value)
        return self
    }
    
    func addIntProperty(key: String, value: Int) -> RudderPropertyBuilder {
        rudderProperty.addIntProperty(key: key, value: value)
        return self
    }

    func addFloatProperty(key: String, value: Float) -> RudderPropertyBuilder {
        rudderProperty.addFloatProperty(key: key, value: value)
        return self
    }
    
    func addDoubleProperty(key: String, value: Double) -> RudderPropertyBuilder {
        rudderProperty.addDoubleProperty(key: key, value: value)
        return self
    }
    
    func addBoolProperty(key: String, value: Bool) -> RudderPropertyBuilder {
        rudderProperty.addBoolProperty(key: key, value: value)
        return self
    }
    
    func addObjectProperty(key: String, value: PropertyObject) -> RudderPropertyBuilder{
        rudderProperty.addObjectProperty(key: key, value: value)
        return self
    }
    
    func addListProperty(key: String, value: [PropertyObject]) -> RudderPropertyBuilder {
        rudderProperty.addListProperty(key: key, value: value)
        return self
    }
    
    func build() throws -> RudderProperty {
        return rudderProperty
    }
}
