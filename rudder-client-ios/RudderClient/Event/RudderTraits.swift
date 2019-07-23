//
//  RudderTraits.swift
//  RudderSample
//
//  Created by Arnab Pal on 12/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//

import Foundation

struct RudderTraits: Encodable {
    var address: TraitsAddress? = nil
    var age: Int? = nil
    var birthday: String? = nil
    var company: TraitsCompany? = nil
    var createdAt: String? = nil
    var description: String? = nil
    var email: String? = nil
    var firstName: String? = nil
    var gender: String? = nil
    var id: String? = nil
    var lastName: String? = nil
    var name: String? = nil
    var phone: String? = nil
    var title: String? = nil
    var userName: String? = nil
    
    enum CodingKeys: String, CodingKey {
        case address = "rl_address"
        case age = "rl_age"
        case birthday = "rl_birthday"
        case company = "rl_company"
        case createdAt = "rl_createdat"
        case description = "rl_description"
        case email = "rl_email"
        case firstName = "rl_firstname"
        case gender = "rl_gender"
        case id = "rl_id"
        case lastName = "rl_lastname"
        case name = "rl_name"
        case phone = "rl_phone"
        case title = "rl_title"
        case userName = "rl_username"
    }
}
