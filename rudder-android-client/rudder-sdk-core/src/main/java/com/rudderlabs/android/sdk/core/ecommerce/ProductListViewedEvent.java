package com.rudderlabs.android.sdk.core.ecommerce;

import com.rudderlabs.android.sdk.core.RudderException;
import com.rudderlabs.android.sdk.core.RudderProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductListViewedEvent extends ECommercePropertyBuilder {
    private String listId;

    public ProductListViewedEvent withListId(String listId) {
        this.listId = listId;
        return this;
    }

    private String category;

    public ProductListViewedEvent withCategory(String category) {
        this.category = category;
        return this;
    }

    private ArrayList<ECommerceProduct> products;

    public ProductListViewedEvent withProducts(List<ECommerceProduct> products) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.addAll(products);
        return this;
    }

    public ProductListViewedEvent withProduct(ECommerceProduct product) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(product);
        return this;
    }

    public ProductListViewedEvent withProducts(ECommerceProduct... products) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        Collections.addAll(this.products, products);
        return this;
    }

    @Override
    public String event() {
        return ECommerceEvents.PRODUCT_LIST_VIEWED;
    }

    @Override
    public RudderProperty build() throws RudderException {
        RudderProperty property = new RudderProperty();
        property.setProperty(ECommerceParamNames.LIST_ID, this.listId);
        property.setProperty(ECommerceParamNames.CATEGORY, this.category);
        property.setProperty(ECommerceParamNames.PRODUCTS, this.products);
        return property;
    }
}
