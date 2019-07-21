//
//  RudderApp.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderApp: Codable {
    var build: String = ""
    var name: String = ""
    var nameSpace : String = ""
    var version: String = ""
    
    enum CodingKeys: String, CodingKey {
        case build = "build"
        case name = "name"
        case nameSpace = "namespace"
        case version = "version"
    }
}
