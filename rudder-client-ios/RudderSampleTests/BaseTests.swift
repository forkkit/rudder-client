//
//  BaseTests.swift
//  RudderSampleTests
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import XCTest
@testable import RudderSample

class BaseTests: XCTestCase {
    let rudderClient: RudderClient = RudderClient().getInstance(endPointUri: "http://9cb63a34.ngrok.io", flushQueueSize: 10)
    override func setUp() {
        Constants.DEBUG = true
    }
    
    override func tearDown() {
        Constants.DEBUG = false
    }
    
    func printError(_error: Error) {
        print("ERROR: " + _error.localizedDescription)
    }
}
