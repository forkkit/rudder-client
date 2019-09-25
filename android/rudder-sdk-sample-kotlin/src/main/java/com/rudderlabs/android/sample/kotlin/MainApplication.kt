package com.rudderlabs.android.sample.kotlin

import android.app.Application
import com.rudderlabs.android.sdk.core.RudderClient

class MainApplication : Application() {
    companion object {
//        private const val WRITE_KEY = "1R3JbxsqWZlbYjJlBxf0ZNWZOH6"
        private const val WRITE_KEY = "1R3Jvg9N1AxVGPoSANqRaz2FovE"
        private const val END_POINT_URI = "https://7dca54a2.ngrok.io"
        lateinit var rudderClient: RudderClient
    }

    override fun onCreate() {
        super.onCreate()
        RudderClient.initiateInstance(this, WRITE_KEY, END_POINT_URI, 30, 10000, 10)
        rudderClient = RudderClient.getInstance()
    }
}