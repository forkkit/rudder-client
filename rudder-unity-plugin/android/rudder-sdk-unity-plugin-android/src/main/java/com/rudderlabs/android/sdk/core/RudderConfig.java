package com.rudderlabs.android.sdk.core;

import android.text.TextUtils;
import android.webkit.URLUtil;

import java.util.ArrayList;

public class RudderConfig {
    private String endPointUri;
    private int flushQueueSize;
    private int dbCountThreshold;
    private int sleepTimeOut;
    private ArrayList<RudderIntegrationFactory> integrations;
    private boolean isDebug;
    private int logLevel;

    private RudderConfig() throws RudderException {
        this(Constants.BASE_URL, Constants.FLUSH_QUEUE_SIZE, Constants.DB_COUNT_THRESHOLD, Constants.SLEEP_TIMEOUT, new ArrayList<RudderIntegrationFactory>(), false, RudderLogger.RudderLogLevel.ERROR);
    }

    RudderConfig(String endPointUri, int flushQueueSize, int dbCountThreshold, int sleepTimeOut, ArrayList<RudderIntegrationFactory> integrations, boolean isDebug, int logLevel) throws RudderException {
        RudderLogger.init(logLevel);
        if (TextUtils.isEmpty(endPointUri)) {
            throw new RudderException("endPointUri can not be null or empty");
        }
        if (!URLUtil.isValidUrl(endPointUri)) {
            throw new RudderException("malformed endPointUri");
        }
        // check if endpoint uri is formatted correctly
        if (!endPointUri.endsWith("/")) endPointUri += "/";
        this.endPointUri = endPointUri;
        if (flushQueueSize < 1 || flushQueueSize > 100) {
            throw new RudderException("flushQueueSize is out of range. Min: 1, Max: 100");
        }
        this.flushQueueSize = flushQueueSize;
        this.integrations = integrations;
        this.isDebug = isDebug;
        this.logLevel = logLevel;
        this.dbCountThreshold = dbCountThreshold;
        this.sleepTimeOut = sleepTimeOut;
    }

    static RudderConfig getDefaultConfig() {
        try {
            return new RudderConfig();
        } catch (RudderException e) {
            RudderLogger.logError(e.getCause());
            return null;
        }
    }

    private ArrayList<RudderIntegrationFactory> getDefaultIntegrations() {
        return new ArrayList<>();
    }

    public String getEndPointUri() {
        return endPointUri;
    }

    public void setEndPointUri(String endPointUri) {
        this.endPointUri = endPointUri;
    }

    public int getFlushQueueSize() {
        return flushQueueSize;
    }

    public void setFlushQueueSize(int flushQueueSize) {
        this.flushQueueSize = flushQueueSize;
    }

    public ArrayList<RudderIntegrationFactory> getIntegrations() {
        return integrations;
    }

    public void setIntegrations(ArrayList<RudderIntegrationFactory> integrations) {
        this.integrations = integrations;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public int getDbCountThreshold() {
        return dbCountThreshold;
    }

    public int getSleepTimeOut() {
        return sleepTimeOut;
    }

    public int getLogLevel() {
        return logLevel;
    }
}
