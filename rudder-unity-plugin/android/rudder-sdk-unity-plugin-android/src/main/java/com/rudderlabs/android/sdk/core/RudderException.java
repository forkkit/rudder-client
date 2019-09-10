package com.rudderlabs.android.sdk.core;

public class RudderException extends Exception {
    private RudderException() {
    }

    public RudderException(String message) {
        super(message);
    }
}
