package com.juanvivas.testapsi.presentation.interfaces

import com.juanvivas.testapsi.presentation.base.interfaces.IBaseBL

interface IHomeBL: IBaseBL {
    fun onSearch(search: String?)
}