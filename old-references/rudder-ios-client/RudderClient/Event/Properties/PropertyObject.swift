//
//  PropertyObject.swift
//  RudderSample
//
//  Created by Arnab Pal on 16/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

enum PropertyObject : Encodable {
    case blank(String?)
    case string(String)
    case int(Int)
    case float(Float)
    case double(Double)
    case bool(Bool)
    case object([String: PropertyObject?])
    case array([PropertyObject])
    
    public func encode(to encoder: Encoder) throws {
        var container = encoder.singleValueContainer()
        switch self {
            case .blank(let initializer): try container.encode(initializer)
            case .string(let string): try container.encode(string)
            case .int(let int): try container.encode(int)
            case .float(let float) : try container.encode(float)
            case .double(let double): try container.encode(double)
            case .bool(let bool): try container.encode(bool)
            case .object(let object): try container.encode(object)
            case .array(let array): try container.encode(array)
        }
    }
}
