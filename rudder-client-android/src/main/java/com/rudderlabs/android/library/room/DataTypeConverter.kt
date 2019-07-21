package com.rudderlabs.android.library.room

import androidx.room.TypeConverter
import com.rudderlabs.android.library.models.porperties.RudderProperty
import com.rudderlabs.android.library.util.fromJson
import com.google.gson.Gson
import java.util.*

class DataTypeConverter {
    @TypeConverter
    fun propertyToJsonConverter(rudderProperty: RudderProperty?): String {
        return Gson().toJson(rudderProperty)
    }

    @TypeConverter
    fun jsonToPropertyConverter(json: String): RudderProperty? {
        return Gson().fromJson(json)
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun timeStampToDate(timeStamp: Long): Date {
        return Date(timeStamp)
    }

    @TypeConverter
    fun integrationPlatformsToSting(list: MutableList<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToIntegrationPlatforms(json: String): MutableList<String> {
        return Gson().fromJson(json)
    }
}
