package com.juanvivas.testapsi.model

import io.realm.RealmObject

open  class Search() : RealmObject() {
    var title: String? = null
    var path: String? = null
    var price: String? = null
    var image: String? = null
}