package com.juanvivas.testapsi.presentation.implementation

import android.app.FragmentTransaction
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.juanvivas.testapsi.R
import com.juanvivas.testapsi.model.App
import com.juanvivas.testapsi.model.Search
import com.juanvivas.testapsi.presentation.base.implementation.BaseView
import com.juanvivas.testapsi.presentation.interfaces.IHomeActivity
import com.juanvivas.testapsi.presentation.interfaces.IHomePresenter
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.enabled
import org.jetbrains.anko.onClick

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
        bt_search.onClick {
            presenter?.onSearch(et_search.text.toString())
            bt_search.enabled = false
        }

    }

    override fun onSuccessSearch(data: ArrayList<Search>) {
        bt_search.enabled = true
        if (data.isNotEmpty()) {
            val adapter = DetailSearchAdapter(data)
            val layoutManager = LinearLayoutManager(App.mContext)
            layoutManager.orientation = LinearLayout.VERTICAL
            rv_list_detail_request.layoutManager = layoutManager
            rv_list_detail_request.adapter = adapter
        }
    }


}
