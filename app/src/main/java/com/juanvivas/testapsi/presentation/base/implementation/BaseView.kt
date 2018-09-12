package com.juanvivas.testapsi.presentation.base.implementation

import android.app.ProgressDialog
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.juanvivas.testapsi.R
import com.juanvivas.testapsi.model.App
import com.juanvivas.testapsi.presentation.base.components.IOnDialogListener
import com.juanvivas.testapsi.presentation.base.interfaces.IBaseView
import com.juanvivas.testapsi.utils.Constants


open class BaseView : AppCompatActivity(), IBaseView, IOnDialogListener {

    private var progressDialog: ProgressDialog? = null
    var mNotificationsReceiver: BroadcastReceiver? = null
    var pathImageCamera: String = ""

    override fun onDialogOkClick(dialogTag: String) {
        closeDialog()
    }

    override fun onDialogCancelClick(dialogTag: String) {
        closeDialog()
    }

    override fun onLinkClick() {
        closeDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.mContext = this

    }

    override fun onResume() {
        //App.mContext = this
        super.onResume()
//        LocalBroadcastManager.getInstance(this@BaseView)
//                .registerReceiver(this!!.mNotificationsReceiver!!, IntentFilter("state internet"))
    }

    override fun onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this!!.mNotificationsReceiver!!)
        super.onDestroy()
    }

    fun showProgress(titleDialog: String?) {
        if (progressDialog != null) {
            closeProgress()
        }
        progressDialog = ProgressDialog(this, R.style.AppCompatAlertDialogStyle)
        progressDialog!!.setMessage(titleDialog)
        progressDialog!!.setCancelable(true)
        progressDialog!!.show()
    }

    fun closeProgress() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }


    protected fun closeDialog() {
        if (progressDialog != null && progressDialog!!.isShowing())
            progressDialog!!.dismiss()
    }
}
