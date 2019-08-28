package com.rudderlabs.android.sdk.core.ecommerce;

public class ProductRemovedEvent extends ProductViewedEvent {
    @Override
    public String event() {
        return ECommerceEvents.PRODUCT_REMOVED;
    }
}
