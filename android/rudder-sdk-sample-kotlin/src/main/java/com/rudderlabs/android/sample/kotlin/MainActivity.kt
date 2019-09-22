package com.rudderlabs.android.sample.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rudderlabs.android.sdk.core.RudderElementBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            MainApplication.rudderClient.track(
                "Simple Kotlin Track with '",
                "test_user_id",
                "{\"category\":\"PlayerShooting_Shoot\",\"label\":null,\"value\":null}",
                "null"
            )
            count += 1
            textView.text = "Count: $count"
        }

        rst.setOnClickListener {
            count = 0
            textView.text = "Count: "
        }
    }
}
