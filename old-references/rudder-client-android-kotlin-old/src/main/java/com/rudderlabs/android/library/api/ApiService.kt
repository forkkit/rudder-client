package com.rudderlabs.android.library.api

import com.rudderlabs.android.library.models.RudderEvent
import com.rudderlabs.android.library.models.RudderEventPayload
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

@JvmSuppressWildcards
interface ApiService {
    @POST("hello")
    suspend fun postEvent(@Body events: RudderEventPayload): Response<String?>

    @POST("hello")
    suspend fun postSingleEvent(@Body event: RudderEvent): Response<String?>
}
