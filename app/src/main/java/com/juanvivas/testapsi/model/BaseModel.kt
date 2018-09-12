package com.juanvivas.testapsi.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.juanvivas.testapsi.utils.toJsonString
import org.json.JSONObject
import java.io.Serializable
import org.json.JSONException

/**
 * Created by exsis on 30/10/17.
 */

open class BaseModel: Serializable {

    companion object {
        fun objectFromJson(json: String, type: Class<out BaseModel>): BaseModel? {
            try {
                val currentObject = Gson().fromJson(json, type)
                currentObject.doPostDeserializer()
                return currentObject
            } catch (e: com.google.gson.JsonSyntaxException) {
                return null
            }
        }
        fun objectFromJson(jsonMap: LinkedTreeMap<String, Any>, type: Class<out BaseModel>): BaseModel? {
            try {
                val jsonObject: JSONObject = JSONObject()

                jsonMap.forEach() {
                    jsonObject.put(it.key, it.value)
                }

                val currentObject = Gson().fromJson(jsonObject.toString(), type)
                currentObject.doPostDeserializer()
                return currentObject
            }catch (e:Exception){
                Log.e("Gson error in", type.toString())
                e.printStackTrace()
                return null
            }
            }


        @Throws(com.google.gson.JsonParseException::class, JSONException::class)
        fun arrayFromJson(array: ArrayList<LinkedTreeMap<Any,Any>>, type: Class<out BaseModel>): ArrayList<out BaseModel> {
            val objectArray = ArrayList<BaseModel>()
            /*val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.get(i).toString()
                objectArray.add(BaseModel.objectFromJson(jsonObject, type))
            }*/
            for (map in array){
                val currentObject = Gson().fromJson(map.toJsonString(), type)
                currentObject.doPostDeserializer()
                objectArray.add(currentObject)
            }

            return objectArray
        }
    }


    fun toJsonString(): String{
        return Gson().toJson(this)
    }

    open fun doPostDeserializer(){

    }



}