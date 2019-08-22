package com.rudderlabs.android.sample.java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rudderlabs.android.sdk.core.*;
import com.rudderlabs.android.sdk.core.ecommerce.CheckoutStartedEvent;
import com.rudderlabs.android.sdk.core.ecommerce.ECommerceOrder;
import com.rudderlabs.android.sdk.core.ecommerce.ECommerceProduct;
import com.rudderlabs.android.sdk.core.ecommerce.OrderCompletedEvent;
import com.rudderlabs.android.sdk.core.ecommerce.ProductAddedToCartEvent;
import com.rudderlabs.android.sdk.core.ecommerce.ProductAddedToWishlistEvent;
import com.rudderlabs.android.sdk.core.ecommerce.ProductListViewedEvent;
import com.rudderlabs.android.sdk.core.ecommerce.ProductRemovedEvent;
import com.rudderlabs.android.sdk.core.ecommerce.ProductSearchedEvent;
import com.rudderlabs.android.sdk.core.ecommerce.ProductViewedEvent;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            RudderElement trackEvent = new RudderElementBuilder().setEventName("Test Track")
                    .setProperty(new TrackPropertyBuilder().setCategory("Test Category")).build();
            MainApplication.getInstance().getRudderClient().track(trackEvent);

            RudderElement pageEvent = new RudderElementBuilder().setEventName("Test Track")
                    .setProperty(new PagePropertyBuilder().setUrl("Test Url")).build();
            MainApplication.getInstance().getRudderClient().page(pageEvent);

            RudderElement screenEvent = new RudderElementBuilder().setEventName("Test Track")
                    .setProperty(new ScreenPropertyBuilder().setScreenName("Test Name")).build();
            MainApplication.getInstance().getRudderClient().screen(screenEvent);

            ECommerceProduct product = new ECommerceProduct("507f1f77bcf86cd799439011",
                    "45790-32",
                    "Games",
                    "Monopoly: 3rd Edition",
                    "Monopoly",
                    "Single User",
                    19f,
                    "USD",
                    1f,
                    "MAY_DEALS_3",
                    1,
                    "https://www.example.com/product/path",
                    "https://www.example.com/product/path.jpg");

            /*
            * EComm section
            * */
            ECommerceOrder order = new ECommerceOrder(
                    "50314b8e9bcf000000000000",
                    "Google Store",
                    30f,
                    25.00f,
                    3f,
                    2f,
                    2.5f,
                    3f,
                    "hasbros",
                    "USD"
            );
            order.setProduct(product);

            MainApplication.getInstance().getRudderClient().track(new ProductSearchedEvent().withQuery("blue hotpants"));
            MainApplication.getInstance().getRudderClient().track(new ProductViewedEvent().withProduct(product));
            MainApplication.getInstance().getRudderClient().track(new ProductListViewedEvent().withProducts(product, product, product));
            MainApplication.getInstance().getRudderClient().track(new ProductAddedToWishlistEvent().withProduct(product));
            MainApplication.getInstance().getRudderClient().track(new ProductAddedToCartEvent().withCartId("test-cart_id").withProduct(product));
            MainApplication.getInstance().getRudderClient().track(new CheckoutStartedEvent("test-order-id").withOrder(order));
            MainApplication.getInstance().getRudderClient().track(new OrderCompletedEvent().withOrder(order).withOrder(order));
            MainApplication.getInstance().getRudderClient().track(new ProductRemovedEvent().withProduct(product));

            MainApplication.getInstance().getRudderClient().flush();
        } catch (RudderException e) {
            e.printStackTrace();
        }
    }
}
