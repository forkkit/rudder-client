package com.rudderlabs.android.library

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.rudderlabs.android.library.models.RudderEvent
import com.rudderlabs.android.library.models.RudderEventBuilder
import com.rudderlabs.android.library.models.RudderTraits
import com.rudderlabs.android.library.models.porperties.RudderTraitsBuilder
import com.rudderlabs.android.library.models.porperties.validateFor
import com.rudderlabs.android.library.repository.EventRepository
import com.rudderlabs.android.library.util.Constants

object RudderClient {
    private var instance: RudderClient? = null
    private var applicationContext: Application? = null
    private val repository: EventRepository by lazy {
        EventRepository()
    }

    /*
    * get instance api
    * */
    @JvmStatic
    internal fun getClient(): RudderClient {
        if (instance == null) throw IllegalStateException("Client is not initialized")
        else return instance!!
    }

    @JvmStatic
    internal fun getApplicationContext(): Application? {
        return applicationContext
    }

    @JvmStatic
    @JvmOverloads
    fun getInstance(
            context: Context,
            flushQueueSize: Int = 10,
            endPointUri: String = Constants.BASE_URL,
            shouldCache: Boolean = true
    ): RudderClient {
        if (instance == null) {
            if (context.applicationContext == null)
                throw NullPointerException("Application context can not be null")

            applicationContext = context.applicationContext as Application

            repository.initiate(applicationContext!!, shouldCache)

            setFlushQueueSize(flushQueueSize)
            setEndPointUri(endPointUri)

            instance = this
        }
        return instance!!
    }


    /*
    * api to get end point uri
    * */
    fun getEndPointUri() = repository.getEndPointUri()

    /*
    * api to get current flush queue size
    * */
    fun getFlushQueueSize() = repository.getFlushQueueSize()

    /*
    * api to set end point uri
    * */
    fun setEndPointUri(endPointUri: String) {
        repository.setEndPointUri(endPointUri)
    }

    /*
    * api to set flush queue size
    * */
    fun setFlushQueueSize(flushQueueSize: Int) {
        repository.setFlushQueueSize(flushQueueSize)
    }

    /*
    * api to send track event
    * */
    fun track(event: RudderEvent?) {
        Log.d("PostEvent", "")
        if (BuildConfig.DEBUG) {
            println("EVENT: ${Gson().toJson(event)}")
        }
        if (event?.message != null) {
            event.message?.let {
                it.validateFor(EventType.TRACK)
                repository.dump(event.also { e -> e.message?.type = EventType.TRACK.value })
            }
        }
    }

    /*
    * api to send page event
    * */
    fun page(event: RudderEvent?) {
        Log.d("PostEvent", "")
        if (event?.message != null) {
            event.message?.let {
                it.validateFor(EventType.PAGE)
                repository.dump(event.also { e -> e.message?.type = EventType.PAGE.value })
            }
        }
    }

    /*
    * api to send screen event
    * */
    fun screen(event: RudderEvent?) {
        Log.d("PostEvent", "")
        if (event?.message != null) {
            event.message?.let {
                it.validateFor(EventType.SCREEN)
                repository.dump(event.also { event -> event.message?.type = EventType.SCREEN.value })
            }
        }
    }

    /*
    * api to fire identify event (traits)
    * */
    fun identify(traits: RudderTraits) {
        repository.dump(
                RudderEventBuilder()
                        .setChannel("Identification Channel")
                        .setEvent("Identify")
                        .setUserId(traits.id!!)
                        .build().also {
                            it.message?.type = EventType.IDENTIFY.value
                            it.message?.context?.traits = traits
                        }
        )
    }

    fun identify(traitsBuilder: RudderTraitsBuilder) {
        this.identify(traitsBuilder.build())
    }

    /*
    * api to flush events regardless of event count
    * */
    fun flush() {
        repository.flush()
    }
}

