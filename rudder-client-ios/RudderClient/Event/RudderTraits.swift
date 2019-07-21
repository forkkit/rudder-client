//
//  RudderTraits.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderTraits: Codable {
    let anonymousId: String = UUID().uuidString.lowercased()
    
    enum CodingKeys: String, CodingKey {
        case anonymousId = "anonymous_id"
    }
}
