package com.rudderlabs.android.library.util

import android.app.Application
import android.os.Build

object PackageDataUtil {
    @JvmStatic
    fun getData(application: Application): PackageData {
        val packageName = application.packageName
        val packageManager = application.packageManager
        val packageInfo = packageManager.getPackageInfo(packageName, 0)

        return PackageData(
                when {
                    Build.VERSION.SDK_INT >= 28 -> packageInfo.longVersionCode.toString()
                    else -> packageInfo.versionCode.toString()
                },
                packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                packageInfo.packageName,
                packageInfo.versionName ?: "1.0.0"
        )
    }
}

data class PackageData(
        val appBuild: String,
        val appName: String,
        val appNameSpace: String,
        val appVersion: String
)