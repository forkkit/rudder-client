package com.rudderlabs.android.library.models

import android.app.Application
import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.os.Build
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.ANDROID_ID
import android.telephony.TelephonyManager
import com.rudderlabs.android.library.BuildConfig
import com.rudderlabs.android.library.util.Constants
import com.rudderlabs.android.library.util.DeviceDataUtil
import com.rudderlabs.android.library.util.PackageDataUtil
import java.util.*

object RudderEventTemplate {
    private var instance: RudderEventTemplate? = null

    @JvmStatic
    internal fun initiateTemplate(applicationContext: Application) {
        instance = this
        populate(applicationContext, instance!!)
    }

    @JvmStatic
    internal fun getTemplate(): RudderEventTemplate {
        if (instance == null) {
            throw IllegalStateException("Rudder client is not initialized")
        }
        return instance!!
    }

    var appBuild: String = ""
    var appName: String = ""
    var appNameSpace: String = ""
    var appVersion: String = ""

    var libraryName: String = ""
    var libraryVersion: String = ""

    var osName: String = ""
    var osVersion: String = ""

    var screenDensity: Int = 0
    var screenWidth: Int = 0
    var screenHeight: Int = 0

    var deviceId: String = ""
    var deviceManufacturer: String = ""
    var deviceModel: String = ""
    var deviceName: String = ""

    var userAgent: String = ""

    var carrier: String = ""

    private fun populate(
        applicationContext: Application?,
        template: RudderEventTemplate,
        callback: ((RudderEventTemplate) -> Unit)? = null
    ) {
        if (applicationContext == null) throw IllegalStateException("Rudder client is not initialized")

        applicationContext.let {
            PackageDataUtil.getData(it).let { data ->
                template.appBuild = data.appBuild
                template.appName = data.appName
                template.appNameSpace = data.appNameSpace
                template.appVersion = data.appVersion
            }

            template.libraryName = BuildConfig.APPLICATION_ID
            template.libraryVersion = BuildConfig.VERSION_NAME

            template.osName = Constants.ANDROID
            template.osVersion = Build.VERSION.RELEASE

            DeviceDataUtil.getData(it).let { data ->
                template.screenDensity = data.screenDensity
                template.screenHeight = data.screenHeight
                template.screenWidth = data.screenWidth
            }

            template.deviceId = fetchDeviceId(it)
            template.deviceManufacturer = Build.MANUFACTURER
            template.deviceModel = Build.MODEL
            template.deviceName = Build.DEVICE

            template.userAgent = System.getProperty("http.agent") ?: ""

            template.carrier = getCarrierName(it)
        }

        callback?.invoke(template)
    }

    private fun getCarrierName(context: Context): String {
        val telephonyManager = context.getSystemService(TELEPHONY_SERVICE) as TelephonyManager?
        return telephonyManager?.networkOperatorName ?: "NA"
    }

    private fun fetchDeviceId(context: Context): String {
        if (Build.VERSION.SDK_INT >= 17) {
            val androidId = getString(context.contentResolver, ANDROID_ID)
            if (!androidId.isNullOrEmpty()
                && "9774d56d682e549c" != androidId
                && "unknown" != androidId
                && "000000000000000" != androidId
            ) {
                return androidId
            }
        }

        // If this still fails, generate random identifier that does not persist across installations
        return UUID.randomUUID().toString()
    }
}