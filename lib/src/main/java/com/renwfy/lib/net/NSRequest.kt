package com.renwfy.lib.net

/**
 * Created by LSD on 2018/7/18.
 * Desc abs请求类
 */
interface NSRequest {
    enum class RequestMethod {
        GET, POST, PUT, DELETE
    }

    fun doRequest(): NSRequest

    fun cancelRequest()

    //fun setParams(param: Map<String, String>)

    //fun setHeaders(header: Map<String, String>)
}