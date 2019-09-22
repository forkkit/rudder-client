package com.rudderlabs.android.library.models.porperties

import com.rudderlabs.android.library.EventType
import com.rudderlabs.android.library.exceptions.MalformedEventException
import com.rudderlabs.android.library.models.RudderMessage
import com.rudderlabs.android.library.models.porperties.ecommerce.ECommerceEvents
import com.rudderlabs.android.library.util.doesNotContainKey

internal fun RudderMessage.validateFor(type: EventType) {
    this.validateProperties()
    when (type) {
        EventType.TRACK -> {
            this.validateEvent()

            if (this.event in ECommerceEvents.values().map { it.value }) {
                when (this.event) {
                    ECommerceEvents.CHECKOUT_STEP_VIEWED.value,
                    ECommerceEvents.CHECKOUT_STEP_COMPLETED.value,
                    ECommerceEvents.PAYMENT_INFO_ENTERED.value -> {
                        this.checkForKey("checkout_id")
                        this.checkForKey("step")
                    }
                    ECommerceEvents.PROMOTION_VIEWED.value,
                    ECommerceEvents.PROMOTION_CLICKED.value -> {
                        this.checkForKey("promotion_id")
                    }
                    ECommerceEvents.ORDER_REFUNDED.value -> {
                        this.checkForKey("order_id")
                    }
                    else -> {
                    }
                }
            } else if (this.properties?.doesNotContainKey("category") == true) {
                throw MalformedEventException("Key \"category\" is required in properties")
            }
        }
        EventType.PAGE -> {

        }
        EventType.SCREEN -> {
            if (this.properties?.doesNotContainKey("name") == true) {
                throw MalformedEventException("Key \"name\" is required in properties")
            }
        }
    }
}

private fun RudderMessage.checkForKey(key: String) {
    if (
            this.properties?.doesNotContainKey(key) == true ||
            this.properties?.getProperty(key) == null
    )
        throw MalformedEventException("Key \"$key\" is required in properties")

    this.checkForKeyValue(key)
}

private fun RudderMessage.checkForKeyValue(key: String) {
    val value = this.properties?.getProperty(key)

    if (value is String? && value.isNullOrEmpty()) {
        throw MalformedEventException("Key \"$key\" is required in properties")
    } else if (value is Double && value.equals(-1.0)) {
        throw MalformedEventException("Key \"$key\" is required in properties")
    }
}

internal fun RudderMessage.validateEvent() {
    if (this.event == null) {
        throw MalformedEventException("Key \"event\" is required for track event")
    }
}

internal fun RudderMessage.validateProperties() {
    if (this.properties == null) {
        throw MalformedEventException("Key \"properties\" is required for track event")
    }
}