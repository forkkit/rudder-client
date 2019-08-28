package com.rudderlabs.android.library.models.porperties

import com.rudderlabs.android.library.exceptions.MalformedEventException

class TrackPropertyBuilder : RudderPropertyBuilder() {
    private var category: String? = null
    fun setCategory(category: String): TrackPropertyBuilder {
        this.category = category
        return this
    }

    private var label: String = ""
    fun setLabel(label: String): TrackPropertyBuilder {
        this.label = label
        return this
    }

    private var value: String = ""
    fun setValue(value: String): TrackPropertyBuilder {
        this.value = value
        return this
    }

    override fun build(): RudderProperty {
        if (category == null)
            throw MalformedEventException("Key \"category\" is required for track event")

        return RudderProperty().also {
            it.addProperty("category", category)
            it.addProperty("label", label)
            it.addProperty("value", value)
        }
    }
}
