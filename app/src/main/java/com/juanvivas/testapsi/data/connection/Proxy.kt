package com.juanvivas.testapsi.data.connection

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.juanvivas.testapsi.R
import com.juanvivas.testapsi.model.App
import com.juanvivas.testapsi.model.BaseModel
import com.juanvivas.testapsi.model.MainContent
import com.juanvivas.testapsi.model.MessageResponse
import com.juanvivas.testapsi.utils.Constants
import com.juanvivas.testapsi.utils.Tools
import com.juanvivas.testapsi.utils.toJsonString
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
                        when (response?.code()) {
                            Constants.HttpCodes.OK -> {
                                var objectResponse: Any? = null
                                if (response.body() != null) {
                                    if (response.body() is LinkedTreeMap<*, *>) {
                                        var content = (response.body() as Map<String, Any?>).getValue("contents")
                                        var mainContent = Any()
                                        for (map in content as ArrayList<LinkedTreeMap<Any, Any>>) {
                                            mainContent = map.getValue("mainContent")
                                        }

                                        for (map in mainContent as ArrayList<LinkedTreeMap<Any, Any>>) {
                                            content = map.getValue("contents")
                                        }
                                        for (map in content as ArrayList<LinkedTreeMap<Any, Any>>) {
                                            mainContent = map.getValue("records")
                                        }
                                        //val model = BaseModel.objectFromJson((response.body() as LinkedTreeMap<Any, Any>).toJsonString(), type)
                                        //responder.onSuccessResponse(model, serviceTag)
                                    } else if (response.body() is ArrayList<*>) {
                                        val array = BaseModel.arrayFromJson(response.body() as ArrayList<LinkedTreeMap<Any, Any>>, type)
                                        responder.onSuccessResponse(array, serviceTag)
                                    } else {
                                        responder.onSuccessResponse(null, serviceTag)

                                    }

                                } else {
                                    responder.onSuccessResponse(objectResponse, serviceTag)
                                }
                            }
                            else -> {
                                val messageResponse: MessageResponse
                                if (response?.errorBody() == null) {
                                    messageResponse = MessageResponse(response?.code()!!, App.mContext!!.resources.getString(R.string.fail_proxy_service))
                                } else {
                                    messageResponse = MessageResponse(response?.code()!!, response.errorBody()?.string())
                                }
                                responder.onFailedResponse(messageResponse, serviceTag)
                            }
                        }
                    }

                    fun getData(mContent: ArrayList<LinkedTreeMap<Any, Any>>, tag: String): Any {
                        var content = Any()
                        for (map in mContent) {
                            content = map.getValue(tag)
                        }
                        return content
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
