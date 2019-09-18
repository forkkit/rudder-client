package com.rudderlabs.android.library.models.porperties

import com.rudderlabs.android.library.exceptions.MalformedEventException

class PagePropertyBuilder : RudderPropertyBuilder() {
    private var title: String = ""
    fun setTitle(title: String): PagePropertyBuilder {
        this.title = title
        return this
    }

    private var url: String? = null
    fun setUrl(url: String): PagePropertyBuilder {
        this.url = url
        return this
    }

    private var path: String = ""
    fun setPath(path: String): PagePropertyBuilder {
        this.path = path
        return this
    }

    private var referrer: String = ""
    fun setReferrer(referrer: String): PagePropertyBuilder {
        this.referrer = referrer
        return this
    }

    private var search: String = ""
    fun setSearch(search: String): PagePropertyBuilder {
        this.search = search
        return this
    }

    private var keywords: String = ""
    fun setKeywords(keywords: String): PagePropertyBuilder {
        this.keywords = keywords
        return this
    }

    override fun build(): RudderProperty? {
        if (url == null) {
            throw MalformedEventException("Key \"url\" is required for track event")
        }
        return RudderProperty().also {
            it.addProperty("title", this.title)
            it.addProperty("url", this.url)
            it.addProperty("path", this.path)
            it.addProperty("referrer", this.referrer)
            it.addProperty("search", this.search)
            it.addProperty("keywords", this.keywords)
        }
    }
}