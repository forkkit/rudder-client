//
//  RudderProperty.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderProperty : Encodable {
    internal var property: [String: PropertyObject] = [:]
    
    func addStringProperty(key: String, value: String) {
        self.property[key] = PropertyObject.string(value)
    }
    
    func addIntProperty(key: String, value: Int) {
        self.property[key] = PropertyObject.int(value)
    }
    
    func addFloatProperty(key: String, value: Float) {
        self.property[key] = PropertyObject.float(value)
    }
    
    func addDoubleProperty(key: String, value: Double) {
        self.property[key] = PropertyObject.double(value)
    }
    
    func addBoolProperty(key: String, value: Bool) {
        self.property[key] = PropertyObject.bool(value)
    }
    
    func addObjectProperty(key: String, value: PropertyObject) {
        self.property[key] = value
    }
    
    func addListProperty(key: String, value: [PropertyObject]) {
        self.property[key] = PropertyObject.array(value)
    }
}
