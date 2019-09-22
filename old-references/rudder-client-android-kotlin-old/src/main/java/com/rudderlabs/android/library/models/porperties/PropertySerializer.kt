package com.rudderlabs.android.library.models.porperties

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class PropertySerializer : JsonSerializer<RudderProperty> {
    override fun serialize(src: RudderProperty?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return Gson().toJsonTree(src?.getPropertyMap())
    }
}