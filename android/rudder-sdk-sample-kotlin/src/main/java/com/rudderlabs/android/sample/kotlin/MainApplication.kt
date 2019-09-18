package com.rudderlabs.android.sample.kotlin

import android.app.Application
import com.rudderlabs.android.sdk.core.RudderClient

class MainApplication : Application() {
    companion object {
        private const val WRITE_KEY = "test_write_key"
        private const val END_POINT_URI = "https://655ada3a.ngrok.io"
        lateinit var rudderClient: RudderClient
    }

    override fun onCreate() {
        super.onCreate()
        RudderClient.initiateInstance(this, WRITE_KEY, END_POINT_URI, 30, 10000, 10)
        rudderClient = RudderClient.getInstance()
    }
}