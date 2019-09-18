package com.rudderlabs.android.library.models.porperties

import com.rudderlabs.android.library.exceptions.MalformedEventException

class ScreenPropertyBuilder : RudderPropertyBuilder() {
    var name: String? = null
    fun setScreenName(name: String): ScreenPropertyBuilder {
        this.name = name
        return this
    }

    override fun build(): RudderProperty? {
        if (this.name == null)
            throw MalformedEventException("Key \"name\" is required in properties")
        return RudderProperty().also {
            it.addProperty("name", name)
        }
    }
}