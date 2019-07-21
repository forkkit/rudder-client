//
//  RudderEvent.swift
//  RudderSample
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderEvent : Encodable {
    var message: RudderMessage = RudderMessage()
    
    func fromBuilder(builder: RudderEventBuilder, template: RudderEventTemplate) -> RudderEvent {
        self.message.initiate(template: template)
        self.message.properties = builder.property
        self.message.event = builder.event
        self.message.channel = builder.channel
        return self
    }
    
    func updateType(type: String) -> RudderEvent {
        self.message.type = type
        return self
    }
}
