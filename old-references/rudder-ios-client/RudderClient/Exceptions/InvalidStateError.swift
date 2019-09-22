//
//  InvalidStateError.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct InvalidStateError: Error {
    let message: String
    
    init(_message: String) {
        self.message = _message
    }
    
    public var localizedDescription: String {
        return message
    }
}

struct MalformedEventError: Error {
    let message: String
    
    init(_message: String) {
        self.message = _message
    }
    
    public var localizedDescription: String {
        return message
    }
}
