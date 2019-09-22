//
//  RudderEventBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderElementBuilder {
    internal var property: RudderProperty = RudderProperty()
    func setPropertyBuilder(builder: RudderPropertyBuilder) throws -> RudderElementBuilder {
        self.property = try builder.build()
        return self
    }
    
    func setProperty(property: RudderProperty) -> RudderElementBuilder {
        self.property = property
        return self
    }
    
    internal var event: String = ""
    func setEvent(event: String) -> RudderElementBuilder {
        self.event = event
        return self
    }
    
    internal var channel: String = ""
    func setChannel(channel: String) -> RudderElementBuilder {
        self.channel = channel
        return self
    }
    
    internal var userId: String = ""
    func setUserId(userId: String) -> RudderElementBuilder {
        self.userId = userId
        return self
    }
    
    func build() -> RudderElement {
        return RudderElement().fromBuilder(builder: self)
    }
}
