package com.juanvivas.testapsi.data.persistence

import com.juanvivas.testapsi.model.Search
import io.realm.RealmResults


interface ISearchDao {

    fun getSearch(): RealmResults<Search>?

    fun insertSearch(search: Search)

    fun insertListSearch(search: ArrayList<Search>)


}