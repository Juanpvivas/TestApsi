package com.juanvivas.testapsi.presentation.implementation

import android.os.Bundle
import com.juanvivas.testapsi.R
import com.juanvivas.testapsi.presentation.base.implementation.BaseView
import com.juanvivas.testapsi.presentation.interfaces.IHomeActivity
import com.juanvivas.testapsi.presentation.interfaces.IHomePresenter

class HomeActivity : BaseView(), IHomeActivity {

    private var presenter: IHomePresenter? = null
    private var search: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this)
        search = "computadora"
    }

    override fun onResume() {
        super.onResume()
        presenter?.onSearch(search)
    }
}
