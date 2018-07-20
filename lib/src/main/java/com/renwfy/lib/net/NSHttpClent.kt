package com.renwfy.lib.net

import com.renwfy.lib.utils.AppLog
import java.net.URLEncoder
import java.util.*

/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
open class NSHttpClent {
    companion object {
        private val Tag = NSHttpClent::class.java.simpleName
        private var BASE_URL = ""

        fun init(baseUrl: String) {
            BASE_URL = baseUrl
        }

        fun get(url: String, pamas: Map<String, String>, callback: NSCallback<*>): NSRequest {
            return get(url, null, pamas, callback)
        }

        fun get(url: String, headers: Map<String, String>?, pamas: Map<String, String>, callback: NSCallback<*>): NSRequest {
            var url = url
            var pamas = pamas
            pamas = addParams(pamas)
            url = buildUrl(url, pamas)
            val request = DefaultRequest(NSRequest.RequestMethod.GET, if (url.contains("http")) url else BASE_URL + url, callback)
            request.headers = setHeader(headers)
            return request.doRequest()
        }

        private fun addParams(params: Map<String, String>?): Map<String, String> {
            var params = params
            if (params == null) {
                params = HashMap()
            }
            return params
        }

        private fun setHeader(header: Map<String, String>?): Map<String, String> {
            var header = header
            if (header == null) {
                header = HashMap()
            }
            return header
        }


        private fun buildUrl(url: String, params: Map<String, String>): String {
            val encodedParams = StringBuilder()
            try {
                for ((key, value) in params) {
                    encodedParams.append(URLEncoder.encode(key, "utf-8"))
                    encodedParams.append('=')
                    encodedParams.append(URLEncoder.encode(value, "utf-8"))
                    encodedParams.append('&')
                }
                return url + "?" + encodedParams.toString()
            } catch (e: Exception) {
                AppLog.e(Tag, e.message)
            }

            return url
        }
    }
}