//
//  RudderDeviceInfo.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderDeviceInfo: Codable {
    var id: String = ""
    var manufacturer: String = ""
    var model: String = ""
    var name: String = ""
    
    enum CodingKeys: String, CodingKey {
        case id = "rl_id"
        case manufacturer = "rl_manufacturer"
        case model = "rl_model"
        case name = "rl_name"
    }
}
