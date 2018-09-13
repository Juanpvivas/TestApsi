package com.juanvivas.testapsi.presentation.implementation

import com.google.gson.internal.LinkedTreeMap
import com.juanvivas.testapsi.data.persistence.SearchDao
import com.juanvivas.testapsi.data.repositories.RepoLiverpool
import com.juanvivas.testapsi.model.App
import com.juanvivas.testapsi.model.MessageResponse
import com.juanvivas.testapsi.model.Search
import com.juanvivas.testapsi.presentation.interfaces.IHomeBL
import com.juanvivas.testapsi.presentation.interfaces.IHomeListener
import com.juanvivas.testapsi.utils.Constants

class HomeBL(val listener: IHomeListener) : IHomeBL {

    override fun onSearch(search: String?) {
        RepoLiverpool.onSearch(search!!, this)
    }

    override fun onSuccessResponse(objectResponse: Any?, serviceTag: Int) {
        when (serviceTag) {
            Constants.ServiceTags.SEARCH -> {
                val data = ArrayList<Search>()
                val value = (objectResponse as ArrayList<LinkedTreeMap<Any, Any>>)
                value.forEach {
                    val mainContent = it["mainContent"]
                    (mainContent as ArrayList<LinkedTreeMap<Any, Any>>).forEach {
                        val content = it["contents"]
                        (content as ArrayList<LinkedTreeMap<Any, Any>>).forEach {
                            val records = it["records"]
                            (records as ArrayList<LinkedTreeMap<Any, Any>>).forEach {
                                val response = Search()
                                val detailsAction = it["detailsAction"] as LinkedTreeMap<Any, Any>
                                response.path = (detailsAction)["recordState"].toString()
                                val attributes = it["attributes"] as LinkedTreeMap<Any, Any>
                                response.title = ((attributes)["product.displayName"] as ArrayList<String>).first().toString()
                                response.price = ((attributes)["sortPrice"] as ArrayList<String>).first().toString()
                                response.image = ((attributes)["sku.thumbnailImage"] as ArrayList<String>).first().toString()
                                data.add(response)
                            }
                        }
                    }
                }
                val searchDao = SearchDao(App.mContext!!)
                searchDao.insertListSearch(data)
                listener.onSuccessSearch(data)
            }
        }
    }

    override fun onFailedResponse(response: MessageResponse, serviceTag: Int) {

    }
}