package com.rudderlabs.android.sdk.core;

import android.database.Cursor;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class NetworkServiceManager {
    static void sendEventToServer(DBPersistentManager dbManager, String endPointUri) throws IOException {
        Cursor cursor = dbManager.retrieveBatch();
        if (cursor != null && cursor.moveToFirst()) {
            String batch;
            int batchId;
            batch = cursor.getString(cursor.getColumnIndex(DBPersistentManager.BATCH));
            batchId = cursor.getInt(cursor.getColumnIndex(DBPersistentManager.BATCH_ID));
            cursor.close();

            if (!endPointUri.endsWith("/")) endPointUri += "/";
            endPointUri += "hello";
            RudderLogger.logInfo("NetworkServiceManager:sendEventToServer: endPointUri: " + endPointUri);

            URL url = new URL(endPointUri);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            OutputStream os = httpConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(batch);
            osw.flush();
            osw.close();
            os.close();
            httpConnection.connect();

            String result;
            BufferedInputStream bis = new BufferedInputStream(httpConnection.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int res = bis.read();
            while (res != -1) {
                baos.write((byte) res);
                res = bis.read();
            }
            result = baos.toString();
            RudderLogger.logInfo("NetworkServiceManager:sendEventToServer: Response: " + result);

            if (result.equalsIgnoreCase("OK")) {
                dbManager.deleteBatch(batchId);
                sendEventToServer(dbManager, endPointUri);
            }
        }
    }
}
