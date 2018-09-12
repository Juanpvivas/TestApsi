package com.juanvivas.testapsi.model

import android.app.Application
import android.content.Context


class App: Application() {
    companion object{
        var mContext: Context? = null
    }
}