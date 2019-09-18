//
//  RudderEvent.swift
//  RudderSample
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderElement : Encodable {
    var message: RudderMessage = RudderMessage()
    
    func fromBuilder(builder: RudderElementBuilder) -> RudderElement {
        self.message.properties = builder.property
        self.message.event = builder.event
        self.message.userId = builder.userId
        self.message.channel = builder.channel
        return self
    }
    
    func updateType(type: String) -> RudderElement {
        self.message.type = type
        return self
    }
    
    func updateTraits(traits: RudderTraits) -> RudderElement {
        self.message.context!.traits = traits
        self.message.type = "identify"
        return self
    }
    
    enum CodingKeys: String, CodingKey {
        case message = "rl_message"
    }
}

extension Date {
    func toTimeStampString() -> String {
        let formatter = DateFormatter()
        formatter.timeZone = TimeZone(identifier: "UTC")
        formatter.dateFormat = "yyyy-MM-dd HH:mm:ssZ"
        return formatter.string(from: self)
    }
}
