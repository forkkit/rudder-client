package com.rudderlabs.android.library.api

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.rudderlabs.android.library.BuildConfig
import com.rudderlabs.android.library.annotation.Exclude
import com.rudderlabs.android.library.models.porperties.PropertySerializer
import com.rudderlabs.android.library.models.porperties.RudderProperty
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.OutputStreamWriter
import java.lang.reflect.Type
import java.nio.charset.Charset


class ApiConverter : Converter.Factory() {
    override fun responseBodyConverter(
            type: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return ResponseBodyConverter()
    }

    override fun requestBodyConverter(
            type: Type,
            parameterAnnotations: Array<Annotation>,
            methodAnnotations: Array<Annotation>,
            retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        val gson = GsonBuilder()
                .registerTypeAdapter(RudderProperty::class.java, PropertySerializer())
                .addSerializationExclusionStrategy(object : ExclusionStrategy {
                    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                        return false
                    }

                    override fun shouldSkipField(field: FieldAttributes?): Boolean {
                        return field?.getAnnotation(Exclude::class.java) != null
                    }

                })
                .create()
        val adapter = gson.getAdapter(TypeToken.get(type))
        return RequestBodyConverter(gson, adapter)
    }
}

private class RequestBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) :
        Converter<T, RequestBody> {
    companion object {
        private val MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8")
        private val UTF_8 = Charset.forName("UTF-8")
    }

    override fun convert(value: T): RequestBody? {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        val byteString = buffer.readByteString()

        if (BuildConfig.DEBUG) {
            println("REQUEST: ${gson.toJson(value)}")
        }

        return RequestBody.create(MEDIA_TYPE, byteString)
    }
}

private class ResponseBodyConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String? {
        return value.string()
    }
}