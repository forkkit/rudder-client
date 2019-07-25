package com.rudderlabs.android.library.models.porperties

import com.rudderlabs.android.library.util.convertToMap

open class RudderProperty {
    private val propertyMap = mutableMapOf<String, Any?>()

    fun getPropertyMap() = propertyMap

    fun containsKey(keyString: String): Boolean {
        return propertyMap.containsKey(keyString)
    }

    fun getProperty(key: String): Any? {
        return if (propertyMap.containsKey(key)) propertyMap[key] else null
    }

    fun addProperty(key: String, value: Any?) {
        propertyMap[key] = value
    }

    fun addProperties(map: Map<String, Any?>) {
        for (item in map) propertyMap[item.key] = item.value
    }

    fun <T> addListProperty(key: String) {
        if (!propertyMap.containsKey(key)) {
            propertyMap[key] = mutableListOf<T?>()
        }
    }

    fun <T> addListItem(key: String, item: T) {
        if (propertyMap.containsKey(key)) {
            (propertyMap[key] as MutableList<T>).add(item)
        }
    }

    fun <T> addListItems(key: String, items: List<T>) {
        if (propertyMap.containsKey(key)) {
            (propertyMap[key] as MutableList<T>).addAll(items)
        }
    }

    fun <T> addAsMap(item: T) {
        addProperties(item.convertToMap())
    }
}