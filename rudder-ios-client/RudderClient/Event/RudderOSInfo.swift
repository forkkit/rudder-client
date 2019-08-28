//
//  RudderOSInfo.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderOSInfo: Codable {
    var name: String = ""
    var version: String = ""
    
    enum CodingKeys: String, CodingKey {
        case name = "rl_name"
        case version = "rl_version"
    }
}
