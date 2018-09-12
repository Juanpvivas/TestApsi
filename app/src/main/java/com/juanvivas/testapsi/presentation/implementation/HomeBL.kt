package com.juanvivas.testapsi.presentation.implementation

import com.juanvivas.testapsi.data.repositories.RepoLiverpool
import com.juanvivas.testapsi.model.MessageResponse
import com.juanvivas.testapsi.presentation.interfaces.IHomeBL
import com.juanvivas.testapsi.presentation.interfaces.IHomeListener

class HomeBL(listener: IHomeListener) : IHomeBL {
    override fun onSearch(search: String?) {
        RepoLiverpool.onSearch(search!!, this)
    }

    override fun onSuccessResponse(objectResponse: Any?, serviceTag: Int) {
        TODO("Recibir clase")
    }

    override fun onFailedResponse(response: MessageResponse, serviceTag: Int) {

    }
}