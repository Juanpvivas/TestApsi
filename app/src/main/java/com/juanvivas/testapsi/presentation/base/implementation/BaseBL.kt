package com.juanvivas.testapsi.presentation.base.implementation

import com.juanvivas.testapsi.model.MessageResponse
import com.juanvivas.testapsi.presentation.base.interfaces.IBaseBL


open class BaseBL(): IBaseBL {
    override fun onSuccessResponse(objectResponse: Any?, serviceTag: Int) {
        //TODO close progrees
    }

    override fun onFailedResponse(response: MessageResponse, serviceTag: Int) {
        //TODO close progrees
        //TODO Show Failes Dialog
    }
}