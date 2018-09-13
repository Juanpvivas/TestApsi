package com.juanvivas.testapsi.presentation.interfaces

import com.juanvivas.testapsi.model.Search
import com.juanvivas.testapsi.presentation.base.interfaces.IOnBaseFinishedListener

interface IHomeListener: IOnBaseFinishedListener {
    fun onSuccessSearch(data: ArrayList<Search>)
}