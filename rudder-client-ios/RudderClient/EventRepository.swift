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
    private var eventBuffer = [RudderEvent]()
    private var endPointUri: String = Constants.END_POINT_URL
    private var flushQueueSize: Int = 10
    static var eventTemplate: RudderEventTemplate? = nil
    
    func initiate(endPointUri: String, flushQueueSize: Int) {
        self.endPointUri = endPointUri
        self.flushQueueSize = flushQueueSize
     
        EventRepository.eventTemplate = RudderEventTemplate.populate()
        
        initiateApiClient()
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
        
        initiateApiClient()
    }
    
    func dump(event: RudderEvent) {
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
        let payload = RudderEventPayload(
            batch: eventBuffer, sent_at: Date()
        )
        
        var urlComponents = URLComponents()
        urlComponents.scheme = "http"
        urlComponents.host = endPointUri
        urlComponents.path = "/test"
        
        guard let url = urlComponents.url else { fatalError("Could not create URL from components") }
        if (Constants.DEBUG) {
            print("URL: " + url.absoluteString)
        }
        
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = "POST"
        
        let encoder = JSONEncoder()
        do {
            let jsonData = try encoder.encode(payload)
            urlRequest.httpBody = jsonData
            if (Constants.DEBUG) {
                print("REQUEST: " , String(bytes: jsonData, encoding: .utf8) ?? "no request")
            }
        } catch {
            print("Error: ", error.localizedDescription)
        }
        
        let config = URLSessionConfiguration.default
        let session = URLSession(configuration: config)
        let task = session.dataTask(with: urlRequest){ (responseData, response, responseError) in
            guard responseError == nil else {
                print("Error: " , responseError?.localizedDescription ?? "unknown error")
                return
            }
            
            if let data = responseData, let utf8Response = String(data: data, encoding: .utf8) {
                print("RESPONSE: ", utf8Response)
            } else {
                print("no response")
            }
        }
        task.resume()
        
        eventBuffer.removeAll()
    }
    
    private func initiateApiClient() {
        // initiate api client for networking calls
        
    }
}
