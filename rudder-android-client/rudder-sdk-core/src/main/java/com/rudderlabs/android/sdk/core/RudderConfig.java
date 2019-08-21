package com.rudderlabs.android.sdk.core;

import android.text.TextUtils;
import android.webkit.URLUtil;

import java.util.ArrayList;

public class RudderConfig {
    private String endPointUri = Constants.BASE_URL;
    private int flushQueueSize = Constants.FLUSH_QUEUE_SIZE;
    private ArrayList<RudderIntegrationFactory> integrations = new ArrayList<>();
    private boolean isDebug = false;
    private int logLevel = RudderLogger.RudderLogLevel.NONE;

    public RudderConfig(String endPointUri) throws RudderException {
        new RudderConfig(endPointUri, Constants.FLUSH_QUEUE_SIZE, getDefaultIntegrations());
    }

    public RudderConfig(int flushQueueSize) throws RudderException {
        new RudderConfig(Constants.BASE_URL, flushQueueSize, getDefaultIntegrations());
    }

    public RudderConfig(ArrayList<RudderIntegrationFactory> integrations) {
        try {
            new RudderConfig(Constants.BASE_URL, Constants.FLUSH_QUEUE_SIZE, integrations);
        } catch (RudderException e) {
            RudderLogger.logError(e.getCause());
        }
    }

    public RudderConfig(String endPointUri, int flushQueueSize) throws RudderException {
        new RudderConfig(endPointUri, flushQueueSize, getDefaultIntegrations());
    }

    public RudderConfig(String endPointUri, int flushQueueSize, boolean isDebug, int logLevel) throws RudderException {
        new RudderConfig(endPointUri, flushQueueSize, getDefaultIntegrations(), isDebug, logLevel);
    }

    public RudderConfig(String endPointUri, int flushQueueSize, ArrayList<RudderIntegrationFactory> integrations) throws RudderException {
        new RudderConfig(endPointUri, flushQueueSize, integrations, false, RudderLogger.RudderLogLevel.NONE);
    }

    public RudderConfig(String endPointUri, int flushQueueSize, ArrayList<RudderIntegrationFactory> integrations, boolean isDebug) throws RudderException {
        new RudderConfig(endPointUri, flushQueueSize, integrations, isDebug, isDebug ? RudderLogger.RudderLogLevel.DEBUG : RudderLogger.RudderLogLevel.NONE);
    }

    public RudderConfig(String endPointUri, int flushQueueSize, ArrayList<RudderIntegrationFactory> integrations, boolean isDebug, int logLevel) throws RudderException {
        RudderLogger.init(logLevel);
        if (TextUtils.isEmpty(endPointUri)) {
            throw new RudderException("endPointUri can not be null or empty");
        }
        if (!URLUtil.isValidUrl(endPointUri)) {
            throw new RudderException("malformed endPointUri");
        }
        this.endPointUri = endPointUri;
        if (flushQueueSize < 1 || flushQueueSize > 100) {
            throw new RudderException("flushQueueSize is out of range. Min: 1, Max: 100");
        }
        this.flushQueueSize = flushQueueSize;
        this.integrations = integrations;
        this.isDebug = isDebug;
        this.logLevel = logLevel;
    }

    static RudderConfig getDefaultConfig() {
        try {
            return new RudderConfig(Constants.BASE_URL, Constants.FLUSH_QUEUE_SIZE);
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
}
