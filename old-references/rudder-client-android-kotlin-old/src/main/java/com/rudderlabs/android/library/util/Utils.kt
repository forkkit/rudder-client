package com.rudderlabs.android.library.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getDate(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.US).also {
            it.timeZone = TimeZone.getTimeZone("UTC")
        }.format(Date())
    }
}