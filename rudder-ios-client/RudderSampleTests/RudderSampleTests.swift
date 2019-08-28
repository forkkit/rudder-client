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
    func testIdentifyEvent() {
        rudderClient.identify(traitsBuilder:
            RudderTraitsBuilder()
            .setCity(city: "New York")
            .setCountry(country: "USA")
            .setPostalCode(postalCode: "ZA22334")
            .setState(state: "New York")
            .setStreet(street: "Wall Street")
            .setAge(age: 25)
            .setBirthday(birthday: "05-09-1997")
            .setCompanyName(companyName: "Rudder Labs")
            .setCompanyId(companyId: "test--company--id")
            .setIndustry(industry: "Software Engg")
            .setDescription(description: "Rudder Labs Company")
            .setEmail(email: "example@gmail.com")
            .setFirstName(firstName: "Example")
            .setGender(gender: "Female")
            .setId(id: "8c3f46c6-2bab-4fa6-b59d-e8d3c8b4045f")
            .setLastName(lastName: "Example")
            .setName(name: "Example Traits")
            .setPhone(phone: "9876543212")
            .setTitle(title: "Mrs")
            .setUserName(userName: "example_traits")
        )
        rudderClient.flush()
        sleep(2)
    }
    
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
            sleep(2)
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
            sleep(2)
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
            sleep(2)
        } catch {
            printError(_error: error)
        }
    }
}
