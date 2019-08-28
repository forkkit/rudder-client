package com.rudderlabs.android.sdk.core;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBPersistentManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "rudder_persistence.db";
    private static final int DB_VERSION = 1;
    private static final String EVENTS_TABLE_NAME = "events";
    static final String BATCH = "batch";
    static final String BATCH_ID = "id";
    private static final String UPDATED = "updated";

    private void createSchema(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS '" + EVENTS_TABLE_NAME + "' ( " +
                "  '" + BATCH_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "  '" + BATCH + "' TEXT NOT NULL, " +
                "  '" + UPDATED + "' INTEGER NOT NULL" +
                ")");
        RudderLogger.logInfo("DBPersistentManager:createSchema: Persistent DB created");
    }

    void saveBatch(String batch) {
        ContentValues values = new ContentValues();
        values.put(BATCH, batch);
        values.put(UPDATED, System.currentTimeMillis());
        SQLiteDatabase database = getWritableDatabase();
        if (database.isOpen()) {
            database.insert(EVENTS_TABLE_NAME, null, values);
            RudderLogger.logInfo("DBPersistentManager:createSchema: batch saved");
        } else {
            RudderLogger.logWarn("DBPersistentManager:saveBatch: Database is not open");
        }
    }

    void deleteBatch(int batchId) {
        SQLiteDatabase database = getWritableDatabase();
        if (database.isOpen()) {
            database.execSQL("DELETE FROM " + EVENTS_TABLE_NAME + " WHERE " + BATCH_ID + "=" + batchId);
            RudderLogger.logInfo("DBPersistentManager:deleteBatch: batch deleted with id" + batchId);
        } else {
            RudderLogger.logWarn("DBPersistentManager:deleteBatch: Database is not open");
        }
    }

    Cursor retrieveBatch() {
        SQLiteDatabase database = getReadableDatabase();
        if (database.isOpen()) {
            return database.rawQuery("SELECT * FROM " + EVENTS_TABLE_NAME + " ORDER BY " + UPDATED + " ASC LIMIT 1", null);
        }
        return null;
    }

    DBPersistentManager(Application application) {
        super(application, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createSchema(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // basically do nothing
    }
}
