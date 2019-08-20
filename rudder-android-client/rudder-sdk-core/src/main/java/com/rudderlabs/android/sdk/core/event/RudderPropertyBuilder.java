package com.rudderlabs.android.sdk.core.event;

import com.rudderlabs.android.sdk.core.RudderException;

public abstract class RudderPropertyBuilder {
    public abstract RudderProperty build() throws RudderException;
}
