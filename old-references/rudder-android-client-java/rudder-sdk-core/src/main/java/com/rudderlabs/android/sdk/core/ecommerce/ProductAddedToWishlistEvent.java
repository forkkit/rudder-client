package com.rudderlabs.android.sdk.core.ecommerce;

import com.rudderlabs.android.sdk.core.RudderException;
import com.rudderlabs.android.sdk.core.RudderProperty;

public class ProductAddedToWishlistEvent extends ProductViewedEvent {
    private ECommerceWishList wishList;

    public ProductAddedToWishlistEvent withWishList(ECommerceWishList wishList) {
        this.wishList = wishList;
        return this;
    }

    public ProductAddedToWishlistEvent withWishListId(String wishListId) {
        if (this.wishList == null) {
            this.wishList = new ECommerceWishList();
        }
        wishList.setWishListId(wishListId);
        return this;
    }

    public ProductAddedToWishlistEvent withWishListName(String wishListName) {
        if (this.wishList == null) {
            this.wishList = new ECommerceWishList();
        }
        wishList.setWishListName(wishListName);
        return this;
    }

    @Override
    public String event() {
        return ECommerceEvents.PRODUCT_ADDED_TO_WISH_LIST;
    }

    @Override
    public RudderProperty build() throws RudderException {
        if (wishList == null) {
            throw new RudderException("Wishlist is not initiated");
        }
        RudderProperty property = super.build();
        property.setProperty(ECommerceParamNames.WISHLIST_ID, wishList.getWishListId());
        property.setProperty(ECommerceParamNames.WISHLIST_NAME, wishList.getWishListName());
        return property;
    }
}
