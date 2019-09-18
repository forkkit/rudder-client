package com.rudderlabs.android.library.models

import com.rudderlabs.android.library.models.porperties.RudderProperty
import com.rudderlabs.android.library.models.porperties.RudderPropertyBuilder

class RudderEventBuilder {
    private var rudderProperty: RudderProperty? = null
    fun setProperty(rudderProperty: RudderProperty): RudderEventBuilder {
        this.rudderProperty = rudderProperty
        return this
    }

    fun setPropertyMap(property: Map<String, Any?>): RudderEventBuilder {
        if (this.rudderProperty == null) this.rudderProperty = RudderProperty()
        this.rudderProperty!!.addProperties(property)
        return this
    }

    fun setPropertyBuilder(builderRudder: RudderPropertyBuilder): RudderEventBuilder {
        this.rudderProperty = builderRudder.build()
        return this
    }

    private var event: String? = null
    fun setEvent(event: String): RudderEventBuilder {
        this.event = event
        return this
    }

    private var userId: String? = null
    fun setUserId(userId: String): RudderEventBuilder {
        this.userId = userId
        return this
    }

    private var channel: String? = null
    fun setChannel(channel: String): RudderEventBuilder {
        this.channel = channel
        return this
    }

    fun build(): RudderEvent {
        return RudderEvent().initiate(RudderEventTemplate.getTemplate())
                .also {
                    it.message?.properties = this.rudderProperty
                    it.message?.userId = this.userId
                    it.message?.event = this.event
                    it.message?.channel = this.channel
                }
    }
}