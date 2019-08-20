package com.rudderlabs.android.sdk.core.event;

import android.text.TextUtils;
import com.rudderlabs.android.sdk.core.RudderException;

public class ScreenPropertyBuilder extends RudderPropertyBuilder {
    private String name;

    public ScreenPropertyBuilder setScreenName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public RudderProperty build() throws RudderException {
        if (TextUtils.isEmpty(name)) {
            throw new RudderException("name can not be empty");
        }
        RudderProperty property = new RudderProperty();
        property.setProperty("name", name);
        return property;
    }
}
