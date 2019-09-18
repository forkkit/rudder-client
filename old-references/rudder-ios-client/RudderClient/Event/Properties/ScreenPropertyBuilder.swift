//
//  ScreenPropertyBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class ScreenPropertyBuilder: RudderPropertyBuilder {
    var name: String? = nil
    func setName(name: String) -> ScreenPropertyBuilder {
        self.name = name
        return self
    }
    
    func buildProperty() throws -> RudderProperty {
        if (self.name == nil) {
            throw MalformedEventError(_message: "Key \"name\" is required in properties")
        }
        
        return try self.addStringProperty(key: "name", value: self.name!).build()
    }
}
