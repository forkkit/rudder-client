package com.rudderlabs.android.library.room

import androidx.room.*
import com.rudderlabs.android.library.models.RudderEvent

@Dao
interface EventDAO {
    @Query("select * from rudder_event")
    suspend fun getAllEvents(): List<RudderEvent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEvent(event: RudderEvent)

    @Delete
    suspend fun clearEvents(map: List<RudderEvent>)

    @Update
    suspend fun updateUnSyncedEvents(list: List<RudderEvent>)

    @Query("select * from rudder_event where isInBuffer=:isSynced")
    suspend fun getEvents(isSynced: Boolean): List<RudderEvent>
}