//
//  TypeValuePair.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct TypeValuePair {
    var type: String = ""
    var value: String = ""
    
    func toProperty() -> PropertyObject {
        return PropertyObject.object([
            "type" : PropertyObject.string(type),
            "value" : PropertyObject.string(value)
            ])
    }
}
