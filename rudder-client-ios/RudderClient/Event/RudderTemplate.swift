//
//  RudderTemplate.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderTemplate {
    var instance: RudderTemplate? = nil
    static var template: RudderEventTemplate? = nil
    
    static func initiate() {
        template = RudderEventTemplate.populate()
    }
}
