package com.rudderlabs.android.library.repository

import android.app.Application
import android.webkit.URLUtil
import androidx.room.Room
import com.rudderlabs.android.library.BuildConfig
import com.rudderlabs.android.library.api.ApiClient
import com.rudderlabs.android.library.api.ApiService
import com.rudderlabs.android.library.exceptions.InvalidQueueSizeException
import com.rudderlabs.android.library.exceptions.InvalidUriException
import com.rudderlabs.android.library.models.RudderEvent
import com.rudderlabs.android.library.models.RudderEventPayload
import com.rudderlabs.android.library.models.RudderEventTemplate
import com.rudderlabs.android.library.room.RudderDatabase
import com.rudderlabs.android.library.util.Constants
import com.rudderlabs.android.library.util.plus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class EventRepository {
    private val eventBuffer = mutableListOf<RudderEvent>()
    private var endPointUri: String = Constants.BASE_URL
    private var flushQueueSize: Int = 10
    private var apiService: ApiService? = null
    private var rudderDb: RudderDatabase? = null
    private var shouldCache = true

    internal fun initiate(applicationContext: Application, shouldCache: Boolean) {
        this.shouldCache = shouldCache
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                if (shouldCache && rudderDb == null) {
                    rudderDb = Room.databaseBuilder(
                            applicationContext, RudderDatabase::class.java, "rudder-local-db"
                    ).build()
                }
                RudderEventTemplate.initiateTemplate(applicationContext)
            }
        }
    }

    internal fun getEndPointUri() = endPointUri

    internal fun getFlushQueueSize() = flushQueueSize

    internal fun setEndPointUri(endPointUri: String) {
        // check for end point url
        if (!URLUtil.isValidUrl(endPointUri)) {
            throw InvalidUriException(message = "Invalid End Point URI")
        }

        this.endPointUri = endPointUri
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                instantiateApiService()
            }
        }
    }

    internal fun setFlushQueueSize(flushQueueSize: Int) {
        // check for flushQueueSize
        if (flushQueueSize < 1 || flushQueueSize > 100) {
            throw InvalidQueueSizeException(message = "Queue size should be between 1 and 100")
        }

        // initially event buffer will ideally be empty
        if (flushQueueSize < eventBuffer.size) flushEvents()
        this.flushQueueSize = flushQueueSize
    }

    internal fun dump(event: RudderEvent) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                event.also { it.isInBuffer = true }.let {
                    if (shouldCache) rudderDb?.getEventDao()?.saveEvent(it)
                    eventBuffer.add(event)

                    if (eventBuffer.size == flushQueueSize) {
                        flushEvents()
                    }
                }
            }
        }
    }

    internal fun flush() {
        flushEvents()
    }

    private fun instantiateApiService() {
        apiService = ApiClient(endPointUri).get().create(ApiService::class.java)
    }

    private fun flushEvents(callback: ((String?) -> Unit)? = null): String? {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (apiService == null) instantiateApiService()
                    val eventsToSync = when (shouldCache) {
                        true -> eventBuffer.toList().plus(rudderDb?.getEventDao()?.getEvents(false))
                        false -> eventBuffer
                    }

                    val payload = RudderEventPayload(events = eventsToSync)

                    val apiResponse = apiService?.postEvent(payload)

                    callback?.invoke(apiResponse?.body())

                    if (apiResponse != null && apiResponse.isSuccessful) {
                        if (shouldCache) rudderDb?.getEventDao()?.clearEvents(eventsToSync)
                        eventBuffer.clear()
                        apiResponse.body().let {
                            if (BuildConfig.DEBUG) {
                                println("RESPONSE: $it")
                            }
                        }
                    } else {
                        if (BuildConfig.DEBUG) {
                            println("ERROR RESPONSE: ${apiResponse?.errorBody()?.string()}")
                        }
                    }
                } catch (exception: Exception) {
                    if (shouldCache) rudderDb?.getEventDao()?.updateUnSyncedEvents(
                            eventBuffer.map { it.also { e -> e.isInBuffer = false } }
                    )
                    eventBuffer.clear()
                    exception.printStackTrace()
                }
            }
        }
        return null
    }
}