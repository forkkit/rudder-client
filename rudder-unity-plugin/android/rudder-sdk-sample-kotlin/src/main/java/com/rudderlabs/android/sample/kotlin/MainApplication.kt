package com.rudderlabs.android.sample.kotlin

import android.app.Application
import com.rudderlabs.android.sdk.core.RudderClient
import com.rudderlabs.android.sdk.core.RudderConfigBuilder

class MainApplication : Application() {
    companion object {
        private const val WRITE_KEY = "test_write_key"
        private const val END_POINT_URI = "https://76231d09.ngrok.io"
        lateinit var rudderClient: RudderClient
    }

    override fun onCreate() {
        super.onCreate()
        rudderClient = RudderClient.getInstance(
            this,
            WRITE_KEY,
            RudderConfigBuilder()
                .withEndPointUri(END_POINT_URI)
                .build()
        )
    }
}