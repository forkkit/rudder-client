package com.rudderlabs.android.sdk.core.ecommerce;

import com.rudderlabs.android.sdk.core.RudderException;
import com.rudderlabs.android.sdk.core.RudderProperty;
import com.rudderlabs.android.sdk.core.util.Utils;

public class OrderCompletedEvent extends ECommercePropertyBuilder {
    private ECommerceOrder order;

    public OrderCompletedEvent withOrder(ECommerceOrder order) {
        this.order = order;
        return this;
    }

    @Override
    public String event() {
        return ECommerceEvents.ORDER_COMPLETED;
    }

    @Override
    public RudderProperty build() throws RudderException {
        RudderProperty property = new RudderProperty();
        property.setProperty(Utils.convertToMap(order));
        return property;
    }
}
