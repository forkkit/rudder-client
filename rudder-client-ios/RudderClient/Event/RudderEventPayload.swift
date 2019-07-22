//
//  RudderEventPayload.swift
//  RudderSample
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderEventPayload : Encodable {
    let batch: [RudderEvent]
    let sent_at : String = Date().toTimeStampString()
    
    func toDictionary() throws -> [String: AnyObject?] {
        let data = try JSONEncoder().encode(self)
        guard let dictionary = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String: AnyObject?] else {
            throw NSError()
        }
        return dictionary
    }
}
