package com.rudderlabs.android.library.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rudderlabs.android.library.models.porperties.RudderProperty

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson(json, object : TypeToken<T>() {}.type)

fun <E> List<E>.plus(list: List<E>?): List<E> {
    return mutableListOf<E>().also {
        it.addAll(this)
        if (list != null) it.addAll(list)
    }
}

fun <T> T.convertToMap(): MutableMap<String, Any?> {
    Gson().let {
        val json = it.toJson(this)
        return it.fromJson(json, object : TypeToken<MutableMap<String, Any?>>() {}.type)
    }
}

fun Map<String, Any?>.merge(map: Map<String, Any?>): MutableMap<String, Any?> {
    val newMap: MutableMap<String, Any?> = mutableMapOf()
    for (key in this.keys) {
        newMap[key] = this[key]
    }
    for (key in map.keys) {
        newMap[key] = map[key]
    }
    return newMap
}

fun RudderProperty.doesNotContainKey(key: String): Boolean {
    return !this.containsKey(key)
}