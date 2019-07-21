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
    let sent_at : Date
}
