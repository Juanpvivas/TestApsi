package com.juanvivas.testapsi.data.connection

import com.google.gson.internal.LinkedTreeMap
import com.juanvivas.testapsi.R
import com.juanvivas.testapsi.model.*
import com.juanvivas.testapsi.utils.Constants
import com.juanvivas.testapsi.utils.Tools

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Proxy<T : BaseModel> {

    companion object {
        fun execute(
                url: String,
                methodType: String,
                body: BaseModel?,
                queries: Map<String, String>?,
                headers: HashMap<String, String>?,
                responder: IResponder,
                serviceTag: Int,
                type: Class<out BaseModel>,
                isArray: Boolean
        ) {
            if (!Tools.isNetworkAvailable(App.mContext!!)) {
                try {
                    val messageResponse: MessageResponse = MessageResponse(401, App.mContext!!.getString(R.string.networkError))
                    responder.onFailedResponse(messageResponse, serviceTag)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            } else {

                val proxyEndpoint: IProxyEndPoints = ProxyAdapter.startConnection(Constants.Url.URL_BASE)
                val requestQueries: Map<String, String>
                val requestHeaders: HashMap<String, String>

                if (queries != null)
                    requestQueries = queries
                else
                    requestQueries = HashMap()

                if (headers != null)
                    requestHeaders = headers
                else
                    requestHeaders = HashMap()

                var call: Call<Any>? = null

                when (methodType) {
                    Constants.HTTPMethod.GET -> call = proxyEndpoint.getData(url, requestQueries, requestHeaders)
                    Constants.HTTPMethod.POST -> {
                        if (body == null)
                            call = proxyEndpoint.postData(url, query = requestQueries, header = requestHeaders)
                        else
                            call = proxyEndpoint.postData(url, query = requestQueries, header = requestHeaders, body = body)
                    }
                }

                call?.enqueue(object : Callback<Any> {

                    override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                        try {
                            when (response?.code()) {

                                Constants.HttpCodes.OK -> {
                                    try {
                                        if (response.body() != null) {
                                            if (response.body() is LinkedTreeMap<*, *>) {
                                                val contents = (response.body() as LinkedTreeMap<Any, Any>).getValue("contents")
                                                responder.onSuccessResponse(contents, serviceTag)
                                            }
                                        }
                                    } catch (ex: Exception) {
                                        ex.printStackTrace()
                                    }
                                }
                            }
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }
                    }

                    override fun onFailure(call: Call<Any>?, t: Throwable?) {
                        val messageResponse = MessageResponse(t?.hashCode()!!, t.toString())
                        responder.onFailedResponse(messageResponse, serviceTag)
                    }
                })

            }
        }
    }
}
