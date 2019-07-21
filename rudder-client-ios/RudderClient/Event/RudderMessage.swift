//
//  RudderMessage.swift
//  RudderSample
//
//  Created by Arnab Pal on 10/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderMessage : Encodable {
    let messageId: String = UUID().uuidString.lowercased()
    var channel: String = ""
    let context : RudderContext = RudderContext()
    var type: String = ""
    let action: String = ""
    let timestamp: String = String(Date().timeIntervalSince1970)
    let anonymousId: String = UUID().uuidString.lowercased()
    var event: String = ""
    var properties: RudderProperty = RudderProperty()
    var integrations: [String] = ["rudderlabs"]
    
    func initiate(template: RudderEventTemplate) {
        self.context.initiate(template: template)
    }
    
    func addIntegration(integration: String) {
        self.integrations.append(integration)
    }
    
    enum CodingKeys: String, CodingKey {
        case messageId = "message_id"
        case channel = "channel"
        case context = "context"
        case type = "type"
        case action = "action"
        case timestamp = "timestamp"
        case anonymousId = "anonymous_id"
        case event = "event"
        case properties = "properties"
        case integrations = "integrations"
    }
    
    func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(messageId, forKey: .messageId)
        try container.encode(channel, forKey: .channel)
        try container.encode(context, forKey: .context)
        try container.encode(type, forKey: .type)
        try container.encode(action, forKey: .action)
        try container.encode(timestamp, forKey: .timestamp)
        try container.encode(anonymousId, forKey: .anonymousId)
        try container.encode(event, forKey: .event)
        try container.encode(properties.property, forKey: .properties)
        try container.encode(integrations, forKey: .integrations)
    }
}
