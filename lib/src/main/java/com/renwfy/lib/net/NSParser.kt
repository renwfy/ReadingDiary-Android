package com.renwfy.lib.net

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import java.util.*

/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
class NSParser {
    val TAG = "NSJsonParser"

    fun <T> parse(json: String?, clazz: Class<T>?, callback: NSCallback<T>) {
        try {
            val gson = Gson()
            val jr = gson.fromJson<JsonResponse>(json, JsonResponse::class.java!!)
            if (TextUtils.isEmpty(json)) {
                callback.onFail(-1, "")
                return
            }
            if (jr.error == 0) {// success
                if (clazz == null) {
                    Log.w(TAG, "null parse class")
                    callback.onSuccess(null)
                } else if (jr.data == null) {
                    Log.w(TAG, json)
                    callback.onSuccess(null)
                } else {
                    if (jr.data!!.isJsonArray()) {
                        val array = jr.data!!.getAsJsonArray()
                        val result = ArrayList<T>()
                        for (i in 0 until array.size()) {
                            result.add(gson.fromJson<T>(array.get(i), clazz))
                        }
                        callback.onSuccess(result, result.size)
                    } else if (jr.data!!.isJsonObject()) {
                        callback.onSuccess(gson.fromJson<T>(jr.data, clazz))
                    } else if (jr.data!!.isJsonPrimitive()) {
                        callback.onSuccess(gson.fromJson<T>(jr.data, clazz))
                    } else {
                        callback.onSuccess(null)
                    }
                }
            } else {
                callback.onFail(jr.error, jr.msg)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "解析异常")
            callback.onFail(-1, "")
        }

    }

    class JsonResponse {
        var error = 0
        var msg: String = ""
        var data: JsonElement? = null
    }
}