package com.juanvivas.testapsi.presentation.implementation

import com.juanvivas.testapsi.model.MessageResponse
import com.juanvivas.testapsi.model.Search
import com.juanvivas.testapsi.presentation.interfaces.IHomeActivity
import com.juanvivas.testapsi.presentation.interfaces.IHomeBL
import com.juanvivas.testapsi.presentation.interfaces.IHomeListener
import com.juanvivas.testapsi.presentation.interfaces.IHomePresenter

class HomePresenter(val view: IHomeActivity) : IHomePresenter, IHomeListener {
    override fun onSuccessSearch(data: ArrayList<Search>) {
        view.onSuccessSearch(data)
    }

    override fun onSearch(search: String?) {
        bl?.onSearch(search)
    }

    var bl: IHomeBL? = null

    init {
        bl = HomeBL(this)
    }

    override fun onFailed(response: MessageResponse) {

    }
}