package com.juanvivas.testapsi.presentation.base.interfaces

import com.juanvivas.testapsi.model.MessageResponse

interface IOnBaseFinishedListener {
    fun onFailed(response: MessageResponse)
}