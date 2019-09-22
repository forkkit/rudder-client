package com.rudderlabs.android.library.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class InstallReferrerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, data: Intent?) {
        println(data)
    }
}