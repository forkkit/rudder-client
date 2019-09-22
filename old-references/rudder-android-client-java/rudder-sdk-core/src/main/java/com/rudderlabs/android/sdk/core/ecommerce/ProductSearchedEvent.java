package com.rudderlabs.android.sdk.core.ecommerce;

import com.rudderlabs.android.sdk.core.RudderProperty;

public class ProductSearchedEvent extends ECommercePropertyBuilder {
    private String query;

    public ProductSearchedEvent withQuery(String query) {
        this.query = query;
        return this;
    }

    @Override
    public RudderProperty build() {
        RudderProperty property = new RudderProperty();
        property.setProperty(ECommerceParamNames.QUERY, query);
        return property;
    }

    @Override
    public String event() {
        return ECommerceEvents.PRODUCTS_SEARCHED;
    }
}
