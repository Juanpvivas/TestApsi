package com.juanvivas.testapsi.presentation.interfaces

import com.juanvivas.testapsi.model.Search

interface IHomeActivity {
    fun onSuccessSearch(data: ArrayList<Search>)
}