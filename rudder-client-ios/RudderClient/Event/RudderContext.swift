//
//  RudderContext.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

class RudderContext: Encodable {
    var app: RudderApp = RudderApp()
    var traits: RudderTraits = RudderTraits()
    var library: RudderLibraryInfo = RudderLibraryInfo()
    var os: RudderOSInfo = RudderOSInfo()
    var screenInfo: RudderScreenInfo = RudderScreenInfo()
    var userAgent: String = ""
    var locale: String = (Locale.current.languageCode ?? "") + "-" + (Locale.current.regionCode ?? "")
    var deviceInfo: RudderDeviceInfo = RudderDeviceInfo()
    var network: RudderNetwork = RudderNetwork()
    
    func initiate(template: RudderEventTemplate) {
        self.app.name = template.appName
        self.app.build = template.appBuild
        self.app.nameSpace = template.appNameSpace
        self.app.version = template.appVersion
        
        self.traits.anonymousId = template.deviceId
        
        self.os.name = template.osName
        self.os.version = template.osVersion
        
        self.screenInfo.density = template.screenDensity
        self.screenInfo.height = template.screenHeight
        self.screenInfo.width = template.screenWidth
        
        self.deviceInfo.name = template.deviceName
        self.deviceInfo.id = template.deviceId
        self.deviceInfo.model = template.deviceModel
        self.deviceInfo.manufacturer = template.deviceManufacturer
        
        self.network.carrier = template.carrier
        
        self.userAgent = template.userAgent
    }
    
    enum CodingKeys: String, CodingKey {
        case app = "rl_app"
        case traits = "rl_traits"
        case library = "rl_library"
        case os = "rl_os"
        case screenInfo = "rl_screen"
        case userAgent = "rl_user_agent"
        case locale = "rl_locale"
        case deviceInfo = "rl_device"
        case network = "rl_network"
    }
}
