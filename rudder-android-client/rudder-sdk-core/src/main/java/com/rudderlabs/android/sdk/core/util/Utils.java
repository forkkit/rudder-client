package com.rudderlabs.android.sdk.core.util;

import android.app.Application;
import android.os.Build;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import static android.provider.Settings.Secure.ANDROID_ID;
import static android.provider.Settings.System.getString;

public class Utils {
    public static String getTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formatter.format(new Date());
    }

    public static String getDeviceId(Application application) {
        if (Build.VERSION.SDK_INT >= 17) {
            String androidId = getString(application.getContentResolver(), ANDROID_ID);
            if (!TextUtils.isEmpty(androidId)
                    && !"9774d56d682e549c".equals(androidId)
                    && !"unknown".equals(androidId)
                    && !"000000000000000".equals(androidId)
            ) {
                return androidId;
            }
        }

        // If this still fails, generate random identifier that does not persist across installations
        return UUID.randomUUID().toString();
    }
}
