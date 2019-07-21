package com.rudderlabs.android.library.api

import com.rudderlabs.android.library.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class ApiClient(private val endPointUri: String) {
    private val retrofit by lazy {
        val httpClient = OkHttpClient.Builder()
        setTimeoutValues(httpClient)
        addLoggerInterceptor(httpClient)
        createRetrofit(httpClient, endPointUri)
    }

    fun get() = retrofit

    private fun createRetrofit(httpClient: OkHttpClient.Builder, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ApiConverter())
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .build()
    }

    private fun setTimeoutValues(httpClient: OkHttpClient.Builder) {
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.writeTimeout(60, TimeUnit.SECONDS)
    }

    private fun addLoggerInterceptor(httpClient: OkHttpClient.Builder) {
        httpClient.addInterceptor {
            if (BuildConfig.DEBUG) println("URL: ${it.request().url()}")
            it.proceed(it.request())
        }
    }
}
