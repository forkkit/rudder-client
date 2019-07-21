//
//  RudderSampleTests.swift
//  RudderSampleTests
//
//  Created by Arnab Pal on 04/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import XCTest
@testable import RudderSample

class RudderSampleTests: BaseTests {
    func testSimpleTrackEvent() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setEvent(event: "Test Track")
                .setProperty(property: try TrackPropertyBuilder()
                    .setCategory(category: "Test Category")
                    .setLabel(label: "Test Label")
                    .setValue(value: "Test Value")
                    .buildProperty())
                .build()
            rudderClient.track(event: rudderEvent)
            rudderClient.flush()
        } catch {
            printError(_error: error)
        }
    }
    
    func testSimplePageEvent() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setProperty(property: try PagePropertyBuilder()
                    .setUrl(url: "http://jsonviewer.stack.hu")
                    .setKeywords(keywords: "Test")
                    .setPath(path: "http://jsonviewer.stack.hu")
                    .setReferrer(referrer: "Test Event")
                    .setTitle(title: "Test Title")
                    .setSearch(search: "Test")
                    .buildProperty())
                .build()
            rudderClient.page(event: rudderEvent)
            rudderClient.flush()
        } catch {
            printError(_error: error)
        }
    }
    
    func testSimpleScreenEvent() {
        do {
            let rudderEvent = RudderEventBuilder()
                .setChannel(channel: "Test Channel")
                .setProperty(property: try ScreenPropertyBuilder()
                    .setName(name: "Test Screen")
                    .buildProperty())
                .build()
            rudderClient.screen(event: rudderEvent)
            rudderClient.flush()
        } catch {
            printError(_error: error)
        }
    }
}
