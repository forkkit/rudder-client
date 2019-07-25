package com.example.androidanalytics

import android.app.Application
import com.rudderlabs.android.library.RudderClient

class MainApplication : Application() {
    companion object {
        var instance: MainApplication? = null
    }

    private var rudderInstance: RudderClient? = null
    fun getRudderInstance() = rudderInstance

    override fun onCreate() {
        super.onCreate()

        instance = this

        rudderInstance = RudderClient.getInstance(context = this, endPointUri = "http://04c20e28.ngrok.io/")
    }
}