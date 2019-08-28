package com.rudderlabs.android.library.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.rudderlabs.android.library.annotation.Exclude
import com.rudderlabs.android.library.models.porperties.RudderProperty
import com.rudderlabs.android.library.room.DataTypeConverter
import com.rudderlabs.android.library.util.Utils
import java.util.*

@Entity(tableName = "rudder_event")
class RudderEvent {
    @PrimaryKey
    @Exclude
    var messageId: String = UUID.randomUUID().toString()

    /*
    * event properties
    * */
    @Embedded(prefix = "message_")
    @SerializedName("rl_message")
    var message: RudderMessage? = null

    /*
    * local database related properties
    * */
    @TypeConverters(DataTypeConverter::class)
    @Exclude
    var createdTime: Date = Date()

    @Exclude
    var isInBuffer: Boolean = true

    /*
    * initializer
    * */
    internal fun initiate(template: RudderEventTemplate): RudderEvent {
        message = RudderMessage().populate(template)
        return this
    }

    fun addIntegrations(platforms: Array<RudderIntegrationPlatform>) {
        message?.integrations?.addAll(platforms.map { it.value })
    }

    fun addIntegration(platform: RudderIntegrationPlatform) {
        message?.integrations?.add(platform.value)
    }
}

class RudderMessage {
    // supported by builder
    @SerializedName("rl_channel")
    var channel: String? = null

    @Embedded(prefix = "context_")
    @SerializedName("rl_context")
    var context: RudderContext? = null

    // supported by builder
    @SerializedName("rl_type")
    var type: String? = null

    @SerializedName("rl_action")
    var action: String? = null

    @SerializedName("rl_message_id")
    var messageId: String = UUID.randomUUID().toString()

    @SerializedName("rl_timestamp")
    var timestamp: String = Utils.getDate()

    @SerializedName("rl_anonymous_id")
    var anonymousId: String? = null

    @SerializedName("rl_user_id")
    var userId: String? = null

    // supported by builder
    @SerializedName("rl_event")
    var event: String? = null

    // supported by builder
    @SerializedName("rl_properties")
    @TypeConverters(DataTypeConverter::class)
    var properties: RudderProperty? = null

    fun getProperty(key: String): Any? {
        return properties?.getProperty(key)
    }

    fun addProperty(key: String, value: Any?) {
        if (properties == null) properties = RudderProperty()
        properties!!.addProperty(key, value)
    }

    fun addProperties(map: Map<String, Any?>) {
        if (properties == null) properties = RudderProperty()
        properties!!.addProperties(map)
    }

    @SerializedName("rl_integrations")
    @TypeConverters(DataTypeConverter::class)
    var integrations: MutableList<String> = mutableListOf(RudderIntegrationPlatform.RUDDERLABS.value)

    fun populate(template: RudderEventTemplate): RudderMessage? {
        this.anonymousId = template.deviceId
        this.channel = "default_channel"
        this.context = RudderContext().populate(template)
        return this
    }
}

class RudderContext {
    @Embedded(prefix = "app_")
    @SerializedName("rl_app")
    var app: RudderApp? = null

    @Embedded(prefix = "traits_")
    @SerializedName("rl_traits")
    var traits: RudderTraits? = null

    @Embedded(prefix = "library_")
    @SerializedName("rl_library")
    var library: RudderLibraryInfo? = null

    @Embedded(prefix = "os_")
    @SerializedName("rl_os")
    var os: RudderOSInfo? = null

    @Embedded(prefix = "screen_")
    @SerializedName("rl_screen")
    var screenInfo: RudderScreenInfo? = null

    @SerializedName("rl_user_agent")
    var userAgent: String? = ""

    @SerializedName("rl_locale")
    var locale: String = Locale.getDefault().let { "${it.language}-${it.country}" }

    @Embedded(prefix = "device_")
    @SerializedName("rl_device")
    var deviceInfo: RudderDeviceInfo? = null

    @Embedded(prefix = "network_")
    @SerializedName("rl_network")
    var network: RudderNetwork? = null

    fun populate(template: RudderEventTemplate): RudderContext {
        return this.also {
            this.app = RudderApp().populate(template)
            this.library = RudderLibraryInfo().populate(template)
            this.os = RudderOSInfo().populate(template)
            this.screenInfo = RudderScreenInfo().populate(template)
            this.userAgent = it.userAgent
            this.deviceInfo = RudderDeviceInfo().populate(template)
            this.network = RudderNetwork().populate(template)
            this.traits = RudderTraits().also { t -> t.anonymousId = template.deviceId }
        }
    }
}

class RudderApp {
    @SerializedName("rl_build")
    var build: String = ""

    @SerializedName("rl_name")
    var name: String = ""

    @SerializedName("rl_namespace")
    var nameSpace: String = ""

    @SerializedName("rl_version")
    var version: String = ""

    fun populate(template: RudderEventTemplate): RudderApp {
        return this.also {
            it.build = template.appBuild
            it.name = template.appName
            it.nameSpace = template.appNameSpace
            it.version = template.appVersion
        }
    }
}


class RudderTraits {
    @SerializedName("rl_anonymous_id")
    var anonymousId: String? = null
    @SerializedName("rl_address")
    @TypeConverters(DataTypeConverter::class)
    var address: TraitsAddress? = null
    @SerializedName("rl_age")
    var age: Int? = null
    @SerializedName("rl_birthday")
    var birthday: String? = null
    @SerializedName("rl_company")
    @TypeConverters(DataTypeConverter::class)
    var company: TraitsCompany? = null
    @SerializedName("rl_createdat")
    var createdAt: String? = null
    @SerializedName("rl_description")
    var description: String? = null
    @SerializedName("rl_email")
    var email: String? = null
    @SerializedName("rl_firstname")
    var firstName: String? = null
    @SerializedName("rl_gender")
    var gender: String? = null
    @SerializedName("rl_id")
    var id: String? = null
    @SerializedName("rl_lastname")
    var lastName: String? = null
    @SerializedName("rl_name")
    var name: String? = null
    @SerializedName("rl_phone")
    var phone: String? = null
    @SerializedName("rl_title")
    var title: String? = null
    @SerializedName("rl_username")
    var userName: String? = null
}

class TraitsAddress {
    @SerializedName("rl_city")
    var city: String = ""
    @SerializedName("rl_country")
    var country: String = ""
    @SerializedName("rl_postalcode")
    var postalCode: String = ""
    @SerializedName("rl_state")
    var state: String = ""
    @SerializedName("rl_street")
    var street: String = ""
}

class TraitsCompany {
    @SerializedName("rl_name")
    var name: String = ""
    @SerializedName("rl_id")
    var id: String = ""
    @SerializedName("rl_industry")
    var industry: String = ""
}

class RudderLibraryInfo {
    @SerializedName("rl_name")
    var name: String = ""

    @SerializedName("rl_version")
    var version: String = ""

    fun populate(template: RudderEventTemplate): RudderLibraryInfo {
        return this.also {
            it.name = template.libraryName
            it.version = template.libraryVersion
        }
    }
}

class RudderOSInfo {
    @SerializedName("rl_name")
    var name: String = ""

    @SerializedName("rl_version")
    var version: String = ""

    fun populate(template: RudderEventTemplate): RudderOSInfo {
        return this.also {
            it.name = template.osName
            it.version = template.osVersion
        }
    }
}

class RudderScreenInfo {
    @SerializedName("rl_density")
    var density: Int = 0

    @SerializedName("rl_width")
    var width: Int = 0

    @SerializedName("rl_height")
    var height: Int = 0

    fun populate(template: RudderEventTemplate): RudderScreenInfo {
        return this.also {
            it.density = template.screenDensity
            it.width = template.screenWidth
            it.height = template.screenHeight
        }
    }
}

class RudderDeviceInfo {
    @SerializedName("rl_id")
    var id: String = ""

    @SerializedName("rl_manufacturer")
    var manufacturer: String = ""

    @SerializedName("rl_model")
    var model: String = ""

    @SerializedName("rl_name")
    var name: String = ""

    fun populate(template: RudderEventTemplate): RudderDeviceInfo {
        return this.also {
            it.id = template.deviceId
            it.manufacturer = template.deviceManufacturer
            it.model = template.deviceModel
            it.name = template.deviceName
        }
    }
}

class RudderNetwork {
    @SerializedName("rl_carrier")
    var carrier: String = ""

    fun populate(template: RudderEventTemplate): RudderNetwork {
        return this.also {
            carrier = template.carrier
        }
    }
}