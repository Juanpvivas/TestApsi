package com.juanvivas.testapsi.data.connection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.juanvivas.testapsi.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit


class ProxyAdapter {

    // static
    companion object {
        fun startConnection(urlBase: String): IProxyEndPoints {
            val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()

            val nullOnEmptyConverterFactory = object : Converter.Factory() {
                fun converterFactory() = this
                override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>,
                                                   retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
                    val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(),
                            type, annotations)

                    override fun convert(value: ResponseBody) = if (value.contentLength() != 0L)
                        nextResponseBodyConverter.convert(value) else null
                }
            }

            val client: OkHttpClient = OkHttpClient.Builder()
                    .connectTimeout(Constants.HTTPMethod.TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Constants.HTTPMethod.TIMEOUT, TimeUnit.SECONDS)
                    .build()

            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(urlBase)
                    .client(client)
                    .addConverterFactory(nullOnEmptyConverterFactory)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create(IProxyEndPoints::class.java)
        }
    }

}