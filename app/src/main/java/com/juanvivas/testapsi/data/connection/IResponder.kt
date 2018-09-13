package com.juanvivas.testapsi.data.connection

import com.juanvivas.testapsi.model.MessageResponse


interface IResponder {
    fun onSuccessResponse(objectResponse: Any?, serviceTag: Int)
    fun onFailedResponse(response: MessageResponse, serviceTag: Int)
}