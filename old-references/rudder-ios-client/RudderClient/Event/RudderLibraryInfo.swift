//
//  RudderLibraryInfo.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderLibraryInfo: Codable {
    let name: String = Constants.LIBRARY_NAME
    let version: String = Constants.LIBRARY_VERSION
    
    enum CodingKeys: String, CodingKey {
        case name = "rl_name"
        case version = "rl_version"
    }
}
