//
//  RudderClient.swift
//  RudderSample
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderClient {
    var instance: RudderClient? = nil
    var eventRepository: EventRepository = EventRepository()
    
    func getInstance(endPointUri: String = Constants.END_POINT_URL, flushQueueSize: Int = Constants.FLUSH_QUEUE_SIZE) -> RudderClient {
        if (instance == nil) {
            instance = self
            
            eventRepository.initiate(endPointUri: endPointUri, flushQueueSize: flushQueueSize)
            
            
        }
        return instance!
    }
    
    func getEndpointUri() -> String {
        return eventRepository.getEndPointUri()
    }
    
    func setEndPointUri(endPointUri: String) {
        eventRepository.setEndPointUri(endPointUri: endPointUri)
    }
    
    func getFlushSize() -> Int {
        return eventRepository.getFlushQueueSize()
    }
    
    func setFlushSize(flushQueueSize: Int) {
        eventRepository.setFlushQueueSize(flushQueueSize: flushQueueSize)
    }
    
    func track(event: RudderEvent)  {
        eventRepository.dump(event: event.updateType(type: "track"))
    }
    
    func page(event: RudderEvent)  {
        eventRepository.dump(event: event.updateType(type: "page"))
    }
    
    func screen(event: RudderEvent)  {
        eventRepository.dump(event: event.updateType(type: "screen"))
    }
    
    func flush() {
        eventRepository.flush()
    }
}
