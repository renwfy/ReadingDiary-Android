package com.renwfy.lib.net

import com.renwfy.lib.utils.AppLog
import com.zhy.http.okhttp.OkHttpUtils


/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
class DefaultRequest : NSRequest {
    var Tag: String = "DefaultRequest"
    var requestTag: String = ""
    var url: String = ""
    var method: NSRequest.RequestMethod
    var callback: NSCallback<*>
    var params: Map<String, String>? = null
    var headers: Map<String, String>? = null

    constructor(method: NSRequest.RequestMethod, url: String, callback: NSCallback<*>) {
        this.method = method
        this.url = url
        this.callback = callback
    }

    override fun cancelRequest() {
        OkHttpUtils.getInstance().cancelTag(requestTag)
    }

    override fun doRequest(): NSRequest {
        requestTag = "RTag:$url"
        if (method === NSRequest.RequestMethod.GET) {
            AppLog.d(Tag, url)
            OkHttpUtils.get().url(url).headers(headers).tag(requestTag).build().execute(callback)
        } else if (method === NSRequest.RequestMethod.POST) {
            if (params != null) {
                AppLog.d(Tag, url + " , " + NSUtil.getStringReqParam(params!!))
            }
            OkHttpUtils.post().url(url).headers(headers).params(params).tag(requestTag).build().execute(callback)
        } else if (method === NSRequest.RequestMethod.PUT) {
            OkHttpUtils.put().url(url).build().execute(callback)
        } else if (method === NSRequest.RequestMethod.DELETE) {
            OkHttpUtils.delete().url(url).build().execute(callback)
        }
        return this
    }
}