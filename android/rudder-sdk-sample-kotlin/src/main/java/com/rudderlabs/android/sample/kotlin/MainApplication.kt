package com.rudderlabs.android.sample.kotlin

import android.app.Application
import com.rudderlabs.android.sdk.core.RudderClient
import com.rudderlabs.android.sdk.core.RudderConfigBuilder
import com.rudderlabs.android.sdk.ecomm.RudderECommerceClient

class MainApplication : Application() {
    companion object {
        private const val WRITE_KEY = "test_write_key"
        private const val END_POINT_URI = "https://81841de4.ngrok.io/"
        lateinit var rudderEcommClient: RudderECommerceClient
    }

    override fun onCreate() {
        super.onCreate()
        rudderEcommClient = RudderECommerceClient.getInstance(
            this, "test_write_key", RudderConfigBuilder().withEndPointUri(
                END_POINT_URI
            ).build()
        )
    }
}