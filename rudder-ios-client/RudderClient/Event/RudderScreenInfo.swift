//
//  RudderScreenInfo.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderScreenInfo: Codable {
    var density: Int = 0
    var width: Int = 0
    var height: Int = 0
    
    enum CodingKeys: String, CodingKey {
        case density = "rl_density"
        case width = "rl_width"
        case height = "rl_height"
    }
}
