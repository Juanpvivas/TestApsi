package com.juanvivas.testapsi.data.persistence

import android.content.Context
import com.juanvivas.testapsi.model.Search
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where


class SearchDao(context: Context) : ISearchDao {

    private var mRealm: Realm? = null

    init {
        mRealm = DatabaseManager().getSharedInstance(context)
    }

    override fun getSearch(): RealmResults<Search>? {
        return mRealm?.where<Search>()!!.findAll()
//        return null
    }

    override fun insertSearch(search: Search) {
        mRealm?.beginTransaction()
        mRealm?.insert(search)
        mRealm?.commitTransaction()
    }

    override fun insertListSearch(search: ArrayList<Search>) {
        search.forEach {
            insertSearch(it)
        }
    }

}