package com.rudderlabs.android.sdk.core.ecommerce;

import com.rudderlabs.android.sdk.core.RudderProperty;

public class ProductAddedToCartEvent extends ProductViewedEvent {
    private String cartId;

    public ProductAddedToCartEvent withCartId(String cartId) {
        this.cartId = cartId;
        return this;
    }

    @Override
    public String event() {
        return ECommerceEvents.PRODUCT_ADDED;
    }

    @Override
    public RudderProperty build() {
        RudderProperty property = super.build();
        property.setProperty(ECommerceParamNames.CART_ID, this.cartId);
        return property;
    }
}
