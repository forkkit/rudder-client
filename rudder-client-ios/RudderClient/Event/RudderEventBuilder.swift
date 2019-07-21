//
//  RudderEventBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderEventBuilder {
    internal var property: RudderProperty = RudderProperty()
    func setPropertyBuilder(builder: RudderPropertyBuilder) throws -> RudderEventBuilder {
        self.property = try builder.build()
        return self
    }
    
    func setProperty(property: RudderProperty) -> RudderEventBuilder {
        self.property = property
        return self
    }
    
    internal var event: String = ""
    func setEvent(event: String) -> RudderEventBuilder {
        self.event = event
        return self
    }
    
    internal var channel: String = ""
    func setChannel(channel: String) -> RudderEventBuilder {
        self.channel = channel
        return self
    }
    
    func build() -> RudderEvent {
        return RudderEvent().fromBuilder(builder: self, template: EventRepository.eventTemplate!)
    }
}
