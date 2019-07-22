//
//  RudderNetwork.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderNetwork: Encodable {
    var carrier: String = ""
    
    enum CodingKeys: String, CodingKey {
        case carrier = "rl_carrier"
    }
}
