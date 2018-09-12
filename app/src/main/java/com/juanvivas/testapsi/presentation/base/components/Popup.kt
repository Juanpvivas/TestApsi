package com.juanvivas.testapsi.presentation.base.components

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView
import com.juanvivas.testapsi.R

class Popup(activity: Activity, view: View, txt: String) {

    private var txt: String = txt
    private var view: View? = view
    private var activity: Activity? = activity
    private var context: Context? = null
    private var popupWindow: PopupWindow? = null


    fun showPopup() {

        context = activity!!.getApplicationContext()
        val viewLocation = IntArray(2)
        val windowManager = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        view!!.getLocationInWindow(viewLocation)
        val width = view!!.getWidth()
        val height = view!!.getHeight()


        val relativeLeft = viewLocation[0]
        val relativeTop = viewLocation[1] + height * 70 / 100


        val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        popupWindow = PopupWindow(
                inflater.inflate(R.layout.help_popup, null, false),
                width - activity!!.getResources().getDimension(R.dimen.widht_popup).toInt(),
                activity!!.getResources().getDimension(R.dimen.height_popup).toInt(),
                true)

        popupWindow!!.setBackgroundDrawable(BitmapDrawable())
        popupWindow!!.setOutsideTouchable(true)
        popupWindow!!.setAnimationStyle(R.style.AnimationPopup)

        popupWindow!!.showAtLocation(view,
                Gravity.NO_GRAVITY,
                relativeLeft + activity!!.getResources().getDimension(R.dimen.widht_popup).toInt() / 2,
                relativeTop)
        val tvPopUp: TextView = popupWindow!!.getContentView().findViewById(R.id.txtHelp)
        tvPopUp.text = txt
        val relativeLayout: RelativeLayout = popupWindow!!.getContentView().findViewById(R.id.rlv2)
        relativeLayout.visibility = View.GONE
        // activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        tvPopUp.setOnClickListener { popupWindow!!.dismiss() }
    }

    fun dimissPopup() {
        popupWindow!!.dismiss()
    }
}