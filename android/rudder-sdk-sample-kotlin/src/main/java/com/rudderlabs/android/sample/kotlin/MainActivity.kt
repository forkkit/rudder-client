package com.rudderlabs.android.sample.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rudderlabs.android.sdk.core.RudderElementBuilder
import com.rudderlabs.android.sdk.core.TrackPropertyBuilder
import com.rudderlabs.android.sdk.ecomm.events.ProductSearchedEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            MainApplication.rudderEcommClient.client.track(
                RudderElementBuilder()
                    .setEventName("test_event")
                    .setProperty(
                        TrackPropertyBuilder()
                            .setCategory("test_category")
                            .build()
                    )
                    .setUserId("test_user_id")
            )

            MainApplication.rudderEcommClient.track(ProductSearchedEvent().withQuery("blue hotpants"))
            count += 1
            textView.text = "Count: $count"
        }

        rst.setOnClickListener {
            count = 0
            textView.text = "Count: "
        }
    }
}
