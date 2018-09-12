package com.juanvivas.testapsi.data.connection

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by exsis on 7/11/17.
 */
interface IProxyEndPoints {

    @FormUrlEncoded
    @POST
    fun oAuth(@Url patch: String, @FieldMap body: Map<String, String>?): Call<Any>


    @GET
    fun getData(@Url patch: String, @QueryMap query: Map<String, String>?, @HeaderMap header: Map<String, String>?): Call<Any>

    @POST
    fun postData(@Url patch: String, @QueryMap query: Map<String, String>?, @HeaderMap header: Map<String, String>?, @Body body: Any): Call<Any>

    @POST
    fun postData(@Url patch: String, @QueryMap query: Map<String, String>?, @HeaderMap header: Map<String, String>?): Call<Any>


}