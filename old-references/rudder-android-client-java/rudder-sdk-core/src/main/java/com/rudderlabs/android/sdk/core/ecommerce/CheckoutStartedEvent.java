package com.rudderlabs.android.sdk.core.ecommerce;

import com.rudderlabs.android.sdk.core.RudderException;
import com.rudderlabs.android.sdk.core.RudderProperty;
import com.rudderlabs.android.sdk.core.util.Utils;

import java.util.Map;

public class CheckoutStartedEvent extends ECommercePropertyBuilder {
    private ECommerceOrder order;

    public CheckoutStartedEvent(String orderId) {
        this.order = new ECommerceOrder(orderId);
    }

    public CheckoutStartedEvent withOrder(ECommerceOrder order) {
        this.order = order;
        return this;
    }

    private ECommerceCheckout checkout;

    public CheckoutStartedEvent withCheckoutId(ECommerceCheckout checkout) {
        this.checkout = checkout;
        return this;
    }

    @Override
    public String event() {
        return ECommerceEvents.CHECKOUT_STARTED;
    }

    @Override
    public RudderProperty build() throws RudderException {
        RudderProperty property = new RudderProperty();
        property.setProperty(Utils.convertToMap(order));
        property.setProperty(Utils.convertToMap(checkout));
        return property;
    }
}
