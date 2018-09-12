package com.juanvivas.testapsi.data.connection

import com.juanvivas.testapsi.model.MessageResponse

/**
 * Created by exsis on 7/11/17.
 */
interface IResponder {
    fun onSuccessResponse(objectResponse: Any?, serviceTag: Int)
    fun onFailedResponse(response: MessageResponse, serviceTag: Int)
}