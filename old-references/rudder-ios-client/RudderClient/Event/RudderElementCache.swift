//
//  RudderElementCache.swift
//  RudderSample
//
//  Created by Arnab Pal on 28/08/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderElementCache {
    private static var cachedContext: RudderContext? = nil;
    
    func initiate() -> Void {
        
    }
    
    static func getCachedContext() -> RudderContext {
        if (cachedContext == nil) {
            // need to throw exception
            cachedContext = RudderContext()
        }
        return cachedContext!;
    }
    
}
