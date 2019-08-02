package com.example.analyticslibrary.torpedo;

import com.example.analyticslibrary.BaseTestCase;
import com.rudderlabs.android.library.models.RudderEvent;
import com.rudderlabs.android.library.models.RudderEventBuilder;
import com.rudderlabs.android.library.models.RudderIntegrationPlatform;
import com.rudderlabs.android.library.models.porperties.RudderProperty;
import com.rudderlabs.android.library.models.porperties.TrackPropertyBuilder;

import org.junit.Test;

public class TorpedoEvents extends BaseTestCase {

    @Test
    public void testUnverifiedRevenue() {
        RudderProperty property = new TrackPropertyBuilder().setCategory("unverified_revenue").build();
        property.addProperty("price", 4.9899997711);
        property.addProperty("productId", "piggy_bank_1");
        property.addProperty("quantity", 1);
        property.addProperty("revenue", 4.9899997711);
        property.addProperty("coin_balance", 12291645);
        property.addProperty("revenueType", "Android");
        property.addProperty("current_module_name", "CasinoGameModule");
        property.addProperty("fb_profile", "1");
        property.addProperty("game_fps", 30);
        property.addProperty("game_name", "JokerWheelSlots");
        property.addProperty("gem_balance", 6408);
        property.addProperty("graphicsQuality", "HD");
        property.addProperty("level", 65);
        property.addProperty("lifetime_gem_balance", 6408);
        property.addProperty("player_total_battles", 864);
        property.addProperty("player_total_shields", 1718);
        property.addProperty("total_payments", 34053);
        property.addProperty("start_date", "2018-11-07");
        property.addProperty("versionSessionCount", 99);
        RudderEvent rudderEvent = new RudderEventBuilder().setEvent("unverified_revenue").setChannel("Test Channel").setProperty(property).build();
        rudderEvent.addIntegration(RudderIntegrationPlatform.AMPLITUDE);
        rudderClient.track(rudderEvent);
        rudderClient.flush();
    }
}
