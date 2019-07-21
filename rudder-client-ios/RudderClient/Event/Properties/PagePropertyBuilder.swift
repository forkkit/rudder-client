//
//  PagePropertyBuilder.swift
//  RudderSample
//
//  Created by Arnab Pal on 17/07/19.
//  Copyright © 2019 Arnab Pal. All rights reserved.
//
import Foundation

class PagePropertyBuilder : RudderPropertyBuilder {
    private var title: String = ""
    func setTitle(title: String) -> PagePropertyBuilder {
        self.title = title
        return self
    }
    
    private var url: String? = nil
    func setUrl(url: String) -> PagePropertyBuilder {
        self.url = url
        return self
    }
    
    private var path: String = ""
    func setPath(path: String) -> PagePropertyBuilder {
        self.path = path
        return self
    }
    
    private var referrer: String = ""
    func setReferrer(referrer: String) -> PagePropertyBuilder {
        self.referrer = referrer
        return self
    }
    
    private var search: String = ""
    func setSearch(search: String) -> PagePropertyBuilder {
        self.search = search
        return self
    }
    
    private var keywords: String = ""
    func setKeywords(keywords: String) -> PagePropertyBuilder {
        self.keywords = keywords
        return self
    }
    
    func buildProperty() throws -> RudderProperty {
        if (self.url == nil){
            throw MalformedEventError(_message: "Key \"url\" is required for page event")
        }
        return try self.addStringProperty(key: "title", value: self.title)
            .addStringProperty(key: "url", value: self.url!)
            .addStringProperty(key: "path", value: self.path)
            .addStringProperty(key: "referrer", value: self.referrer)
            .addStringProperty(key: "search", value: self.search)
            .addStringProperty(key: "keywords", value: self.keywords)
            .build()
    }
}
