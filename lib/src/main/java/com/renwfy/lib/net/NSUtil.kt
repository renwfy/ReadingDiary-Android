package com.renwfy.lib.net

/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
object NSUtil {
    fun getStringReqParam(params: Map<String, String>): String {
        val stringBuilder = StringBuilder()
        for ((key, value) in params) {
            stringBuilder.append(key)
            stringBuilder.append('=')
            stringBuilder.append(value)
            stringBuilder.append('&')
        }
        var string = stringBuilder.toString()
        string = string.substring(0, string.length - 1)
        return string
    }
}