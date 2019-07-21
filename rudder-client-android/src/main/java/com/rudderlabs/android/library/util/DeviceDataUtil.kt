package com.rudderlabs.android.library.util

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import java.lang.Exception

object DeviceDataUtil {
    @JvmStatic
    fun getData(application: Application): DeviceData {
        try {
            val manager = application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = manager.defaultDisplay
            val displayMetrics = DisplayMetrics()
            display.getMetrics(displayMetrics)
            return DeviceData(
                    displayMetrics.density.toInt(),
                    displayMetrics.widthPixels,
                    displayMetrics.heightPixels
            )
        } catch (exception: Exception) {
            return DeviceData(2, 1920, 1080)
        }
    }
}

data class DeviceData(val screenDensity: Int, val screenHeight: Int, val screenWidth: Int)