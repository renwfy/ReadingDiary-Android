package com.renwfy.lib.net

import com.zhy.http.okhttp.callback.Callback
import okhttp3.Response
import java.util.*

/**
 * Created by LSD on 2018/7/18.
 * Desc String 解析类，主要处理header
 */
abstract class OkCallback : Callback<String>() {
    override fun parseNetworkResponse(response: Response, id: Int): String {
        //headers
        val headers = HashMap<String, String>()
        val keys = response.headers().names()
        val iterator = keys.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            val value = response.header(key, "")
            headers[key] = value
        }
        onHeader(headers)
        //respons
        return response.body().string()
    }

    protected open fun onHeader(headers: Map<String, String>) {}
}