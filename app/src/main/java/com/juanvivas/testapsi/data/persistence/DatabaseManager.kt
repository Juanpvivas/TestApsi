package com.juanvivas.testapsi.data.persistence

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class DatabaseManager {

    private var mRealm: Realm? = null
    private var mContexto: Context? = null
    private var sharedInstance: DatabaseManager? = null

    fun setContext(context: Context): Realm {
        mContexto = context
        Realm.init(context)
        return Realm.getInstance(RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("default.realm")
                .build())

    }

    fun getSharedInstance(context: Context): Realm {
        if (sharedInstance == null) {
            sharedInstance = DatabaseManager()
            mRealm = sharedInstance!!.setContext(context)
        }
        return mRealm!!
    }
}