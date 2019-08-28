package com.rudderlabs.android.library.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rudderlabs.android.library.models.RudderEvent

@Database(entities = [RudderEvent::class], version = 1, exportSchema = false)
abstract class RudderDatabase : RoomDatabase() {
    abstract fun getEventDao(): EventDAO
}

