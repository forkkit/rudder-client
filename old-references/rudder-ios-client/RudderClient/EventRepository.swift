//
//  RudderEventRepository.swift
//  RudderSample
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Alamofire
import Foundation

internal class EventRepository {
    private var eventBuffer = [RudderElement]()
    private var endPointUri: String = Constants.END_POINT_URL
    private var flushQueueSize: Int = 10
    
    func initiate(endPointUri: String, flushQueueSize: Int) {
        self.endPointUri = endPointUri
        self.flushQueueSize = flushQueueSize
    }
    
    func getFlushQueueSize() -> Int {
        return self.flushQueueSize
    }
 
    func setFlushQueueSize(flushQueueSize: Int) {
        if flushQueueSize < self.flushQueueSize {
            flush()
        }
        
        self.flushQueueSize = flushQueueSize
    }
    
    func getEndPointUri() -> String {
        return self.endPointUri
    }
    
    func setEndPointUri(endPointUri: String) {
        self.endPointUri = endPointUri
    }
    
    func dump(event: RudderElement) {
        if (Constants.DEBUG) {
            do {
                try print("EVENT: " , String(bytes: JSONEncoder().encode(event), encoding: .utf8) ?? "no event")
            } catch {
                // ignored
            }
        }
        
        eventBuffer.append(event)
        
        if (eventBuffer.count == self.flushQueueSize) {
            self.flushEvents()
        }
    }
    
    func flush() {
        self.flushEvents()
    }
    
    private func flushEvents() {
        let payload = RudderPayload(batch: eventBuffer)
        
        do {
            let url = endPointUri+"/hello"
            if (Constants.DEBUG) {
                print("URL: " + url)
            }
            
            Alamofire.request(url, method: .post, parameters: try payload.toDictionary() as Parameters, encoding: JSONEncoding.prettyPrinted, headers: nil)
                .responseString { dataResponse in
                    print("RESPONSE: " + dataResponse.description)
                 
                    if (dataResponse.error != nil) {
                        print("ERROR: " + dataResponse.error!.localizedDescription)
                    }
                    
                    self.eventBuffer.removeAll()
                }
        } catch {
            print("ERROR: " + error.localizedDescription)
        }
    }
}
    
extension DateFormatter {
    static let dateFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateFormat = "dd-MM-yyyy HH:mm:ssZ"
        return formatter
    }()
}
