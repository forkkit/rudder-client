package com.example.analyticslibrary;

import com.rudderlabs.android.library.models.RudderEvent;
import com.rudderlabs.android.library.models.RudderEventBuilder;
import com.rudderlabs.android.library.models.porperties.PagePropertyBuilder;
import com.rudderlabs.android.library.models.porperties.RudderTraitsBuilder;
import com.rudderlabs.android.library.models.porperties.ScreenPropertyBuilder;
import com.rudderlabs.android.library.models.porperties.TrackPropertyBuilder;
import com.rudderlabs.android.library.models.porperties.TypeValuePair;
import com.rudderlabs.android.library.models.porperties.ecommerce.ECommerceCartBuilder;
import com.rudderlabs.android.library.models.porperties.ecommerce.ECommerceEvents;
import com.rudderlabs.android.library.models.porperties.ecommerce.ECommerceProduct;
import com.rudderlabs.android.library.models.porperties.ecommerce.ECommercePromotion;
import com.rudderlabs.android.library.models.porperties.ecommerce.ECommercePropertyBuilder;

import org.junit.Test;

public class SinglePointTestCase extends BaseTestCase {
    private ECommerceProduct dummyProduct;
    private ECommercePromotion dummyPromotion;

    @Override
    public void setup() throws InterruptedException {
        super.setup();
        dummyProduct = new ECommerceProduct(
                "507f1f77bcf86cd799439011",
                "45790-32",
                "Games",
                "Monopoly: 3rd Edition",
                "Monopoly",
                "Single User",
                19f,
                1f,
                "MAY_DEALS_3",
                1,
                "https://www.example.com/product/path",
                "https://www.example.com/product/path.jpg"
        );

        dummyPromotion = new ECommercePromotion(
                "promo_1",
                "top_banner_2",
                "75% store-wide shoe sale",
                "home_banner_top"
        );
    }

    private void createCart(ECommerceCartBuilder builder) {
        builder.createCart("skdjsidjsdkdj29j")
                .addProductToCart(dummyProduct)
                .addProductToCart(dummyProduct)
                .addProductToCart(dummyProduct);
    }

    private void createOrder(ECommerceCartBuilder builder) {
        builder.createOrder(
                "50314b8e9bcf000000000000",
                "Google Store",
                30f,
                25.00f,
                3f,
                2f,
                2.5f,
                "hasbros",
                "USD"
        );
    }

    private void updateOrder(ECommerceCartBuilder builder) {
        builder.updateOrder(
                "Google Store",
                27.50f,
                30f,
                25.00f,
                3f,
                2f,
                2.5f,
                "hasbros",
                "USD"
        );
    }

    @Test
    public void testAllEvents() throws InterruptedException {
//        identify
        rudderClient.identify(
                new RudderTraitsBuilder()
                        .setCity("New York")
                        .setCountry("USA")
                        .setPostalCode("ZA22334")
                        .setState("New York")
                        .setStreet("Wall Street")
                        .setAge(25)
                        .setBirthday("05-09-1997")
                        .setCompanyName("Rudder Labs")
                        .setCompanyId("test--company--id")
                        .setIndustry("Software Engg")
                        .setDescription("Rudder Labs Company")
                        .setEmail("example@gmail.com")
                        .setFirstName("Example")
                        .setGender("Female")
                        .setId("8c3f46c6-2bab-4fa6-b59d-e8d3c8b4045f")
                        .setLastName("Traits")
                        .setName("Example Traits")
                        .setPhone("9876543212")
                        .setTitle("Mrs")
                        .setUserName("example_traits")
                        .build()
        );

//        simple track
        RudderEvent trackViewEvent = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent("Test Track")
                .setPropertyBuilder(new TrackPropertyBuilder()
                        .setCategory("Test Category")
                        .setLabel("Test Label")
                        .setValue("Test Value"))
                .build();
        rudderClient.track(trackViewEvent);

//        page
        RudderEvent pageViewEvent = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setPropertyBuilder(new PagePropertyBuilder()
                        .setUrl("http://jsonviewer.stack.hu")
                        .setKeywords("Test")
                        .setPath("http://jsonviewer.stack.hu")
                        .setReferrer("Test Event")
                        .setTitle("Test Title")
                        .setSearch("Test"))
                .build();
        rudderClient.page(pageViewEvent);

//        screen
        RudderEvent screenViewEvent = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setPropertyBuilder(new ScreenPropertyBuilder()
                        .setScreenName("Test Screen"))
                .build();
        rudderClient.screen(screenViewEvent);


        RudderEvent event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PRODUCTS_SEARCHED.getValue())
                .setPropertyBuilder(new ECommercePropertyBuilder().addQuery("blue hotpants"))
                .build();
        rudderClient.track(event);

        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PRODUCT_LIST_VIEWED.getValue())
                .setPropertyBuilder(
                        new ECommercePropertyBuilder()
                                .addListId("hot_deals_1")
                                .addCategory("Deals")
                                .addProduct(dummyProduct)
                                .addProduct(dummyProduct)
                                .addProduct(dummyProduct)
                )
                .build();
        rudderClient.track(event);


        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PRODUCT_LIST_FILTERED.getValue())
                .setPropertyBuilder(
                        new ECommercePropertyBuilder()
                                .addListId("todays_deals_may_11_2016")
                                .addCategory("Deals")
                                .addProduct(dummyProduct)
                                .addProduct(dummyProduct)
                                .addProduct(dummyProduct)
                                .addFilter(new TypeValuePair("department", "beauty"))
                                .addFilter(new TypeValuePair("price", "under-$25"))
                                .addSortItem(new TypeValuePair("price", "desc"))
                )
                .build();
        rudderClient.track(event);


        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PROMOTION_VIEWED.getValue())
                .setPropertyBuilder(new ECommercePropertyBuilder().addPromotion(dummyPromotion))
                .build();
        rudderClient.track(event);


        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PROMOTION_CLICKED.getValue())
                .setPropertyBuilder(new ECommercePropertyBuilder().addPromotion(dummyPromotion))
                .build();
        rudderClient.track(event);


        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PRODUCT_CLICKED.getValue())
                .setPropertyBuilder(
                        new ECommercePropertyBuilder().addProductViewed(dummyProduct)
                )
                .build();
        rudderClient.track(event);


        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PRODUCT_VIEWED.getValue())
                .setPropertyBuilder(
                        new ECommercePropertyBuilder().addProductViewed(dummyProduct)
                )
                .build();
        rudderClient.track(event);


        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PRODUCT_ADDED.getValue())
                .setProperty(
                        ECommerceCartBuilder.instance()
                                .createCart("skdjsidjsdkdj29j")
                                .addProductToCart(dummyProduct)
                                .buildProductProperty()
                )
                .build();
        rudderClient.track(event);


        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PRODUCT_REMOVED.getValue())
                .setProperty(
                        ECommerceCartBuilder.instance()
                                .createCart("skdjsidjsdkdj29j")
                                .addProductToCart(dummyProduct)
                                .addProductToCart(dummyProduct)
                                .addProductToCart(dummyProduct)
                                .addProductToCart(dummyProduct)
                                .removeProductFromCart(dummyProduct)
                                .buildProductProperty()
                )
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder1 = ECommerceCartBuilder.instance();
        createCart(builder1);
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.CART_VIEWED.getValue())
                .setProperty(builder1.buildCartProperty())
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder2 = ECommerceCartBuilder.instance();
        createCart(builder2);
        createOrder(builder2);
        builder2.startCheckout("50314b8e9bcf000000000000");
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.CHECKOUT_STARTED.getValue())
                .setProperty(builder2.buildOrderProperty())
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder3 = ECommerceCartBuilder.instance();
        createCart(builder3);
        createOrder(builder3);
        builder3.startCheckout("50314b8e9bcf000000000000");
        builder3.updateCheckout(2, "Fedex", "Visa");
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.CHECKOUT_STEP_VIEWED.getValue())
                .setProperty(builder3.buildCheckoutProperty())
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder4 = ECommerceCartBuilder.instance();
        createCart(builder4);
        createOrder(builder4);
        builder4.startCheckout("50314b8e9bcf000000000000");
        builder4.updateCheckout(2, "Fedex", "Visa");
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.CHECKOUT_STEP_COMPLETED.getValue())
                .setProperty(builder4.buildCheckoutProperty())
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder5 = ECommerceCartBuilder.instance();
        createCart(builder5);
        createOrder(builder5);
        builder5.startCheckout("50314b8e9bcf000000000000");
        builder5.updateCheckout(2, "Fedex", "Visa");
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.PAYMENT_INFO_ENTERED.getValue())
                .setProperty(builder5.buildCheckoutProperty())
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder6 = ECommerceCartBuilder.instance();
        createCart(builder6);
        createOrder(builder6);
        updateOrder(builder6);
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.ORDER_UPDATED.getValue())
                .setProperty(builder6.buildOrderProperty())
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder7 = ECommerceCartBuilder.instance();
        createCart(builder7);
        createOrder(builder7);
        updateOrder(builder7);
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.ORDER_COMPLETED.getValue())
                .setProperty(builder7.buildOrderProperty())
                .build();
        rudderClient.track(event);

        ECommerceCartBuilder builder8 = ECommerceCartBuilder.instance();
        createCart(builder8);
        createOrder(builder8);
        builder8.processFullRefund();
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.ORDER_REFUNDED.getValue())
                .setProperty(builder8.buildForFullRefund())
                .build();
        rudderClient.track(event);

        ECommerceCartBuilder builder9 = ECommerceCartBuilder.instance();
        createCart(builder9);
        createOrder(builder9);
        builder9.processPartialRefund(dummyProduct, dummyProduct);
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.ORDER_REFUNDED.getValue())
                .setProperty(builder9.buildForPartialReturn(30, "USD"))
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder10 = ECommerceCartBuilder.instance();
        createCart(builder10);
        createOrder(builder10);
        builder10.startCheckout("50314b8e9bcf000000000000");
        updateOrder(builder10);
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.ORDER_CANCELLED.getValue())
                .setProperty(builder10.buildOrderProperty())
                .build();
        rudderClient.track(event);


        ECommerceCartBuilder builder11 = ECommerceCartBuilder.instance();
        createCart(builder11);
        createOrder(builder11);
        builder11.addCoupon("may_deals_2016");
        event = new RudderEventBuilder()
                .setChannel("Test Channel")
                .setEvent(ECommerceEvents.COUPON_ENTERED.getValue())
                .setProperty(builder11.buildCouponProperty())
                .build();
        rudderClient.track(event);

//        flush
        rudderClient.flush();

        Thread.sleep(2000);
    }
}
