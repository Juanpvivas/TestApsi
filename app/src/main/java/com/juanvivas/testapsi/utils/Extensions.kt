package com.juanvivas.testapsi.utils

import com.google.gson.internal.LinkedTreeMap


fun LinkedTreeMap<Any, Any>.toJsonString(): String {

    var jsonString = "{"
    var count = 0

    for (i in this) {

        jsonString += "\"" + i.key + "\" : "
        if (i.value is String) {
            jsonString += "\"" + i.value + "\""
        } else if (i.value is LinkedTreeMap<*, *>) {
            jsonString += (i.value as LinkedTreeMap<Any, Any>).toJsonString()
        } else if (i.value is ArrayList<*>) {
            val array = i.value as ArrayList<LinkedTreeMap<Any, Any>>
            if (array.isEmpty()) {
                jsonString += "[]"
            } else {
                jsonString += "["
                var countArray = 0
                for (j in array) {
                    //TODO implementar con diferentes tipos de datos
                    jsonString += j.toJsonString()
                    countArray++
                    if (countArray < array.size) {
                        jsonString += ","
                    }
                }
                jsonString += "]"
            }

        } else {
            jsonString += i.value
        }
        count++
        if (count < this.size) {
            jsonString += ","
        }
    }
    jsonString += "}"
    return jsonString
}