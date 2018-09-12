package com.juanvivas.testapsi.data.repositories

import com.juanvivas.testapsi.data.connection.IResponder
import com.juanvivas.testapsi.data.connection.Proxy
import com.juanvivas.testapsi.model.Search
import com.juanvivas.testapsi.utils.Constants

class RepoLiverpool {

    companion object {

        fun onSearch(search: String, responder: IResponder) {
            val url: String = Constants.Url.TIENDA
            val queries: HashMap<String, String> = HashMap()

            queries[Constants.OAuth.Keys.CRITERIO] = search
            queries[Constants.OAuth.Keys.KEY] = Constants.OAuth.Keys.VALUE_KEY

            Proxy.execute(url = url,
                    methodType = Constants.HTTPMethod.GET,
                    body = null,
                    queries = queries,
                    headers = null,
                    responder = responder,
                    serviceTag = Constants.ServiceTags.SEARCH,
                    type = Search::class.java,
                    isArray = false
            )
        }
    }
}