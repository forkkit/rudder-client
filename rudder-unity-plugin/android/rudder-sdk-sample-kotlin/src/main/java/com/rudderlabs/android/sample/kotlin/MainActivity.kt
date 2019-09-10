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
                RudderElementBuilder()
                    .setEventName("Simple Kotlin Track with '")
                    .build()
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
