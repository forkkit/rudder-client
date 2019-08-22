package com.rudderlabs.android.sdk.core.ecommerce;

import com.rudderlabs.android.sdk.core.RudderProperty;
import com.rudderlabs.android.sdk.core.util.Utils;

public class ProductViewedEvent extends ECommercePropertyBuilder {
    private ECommerceProduct product;

    public ProductViewedEvent withProduct(ECommerceProduct product) {
        this.product = product;
        return this;
    }

    @Override
    public String event() {
        return ECommerceEvents.PRODUCT_VIEWED;
    }

    @Override
    public RudderProperty build() {
        RudderProperty property = new RudderProperty();
        property.setProperty(Utils.convertToMap(product));
        return property;
    }
}
