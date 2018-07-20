package com.renwfy.lib.net

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.renwfy.lib.activity.BaseActivity
import com.renwfy.lib.utils.AppLog
import com.renwfy.lib.utils.AppTips
import okhttp3.Call
import okhttp3.Request
import java.lang.Exception

/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
open class NSCallback<T> : OkCallback {
    open interface NetRequestListener {
        fun showProgressDialog(msg: String)

        fun hideProgressDialog()

        fun onLoadError()
    }

    var Tag = "NSCallback"
    lateinit var context: Context
    lateinit var clazz: Class<T>
    var showProgressBar: Boolean = false
    var msg: String = ""
    lateinit var netRequestListener: NetRequestListener
    var nsParser = NSParser()

    //构造函数
    constructor(context: Context, clazz: Class<T>) {
        this.context = context
        this.clazz = clazz
        if (context is NSCallback.NetRequestListener) {
            netRequestListener = context
        }
    }

    constructor(context: Context, clazz: Class<T>, showProgressBar: Boolean, msg: String) {
        this.context = context
        this.clazz = clazz
        this.showProgressBar = showProgressBar
        this.msg = msg
        if (context is NSCallback.NetRequestListener) {
            netRequestListener = context as NetRequestListener
        }
    }

    override fun onBefore(request: Request?, id: Int) {
        super.onBefore(request, id)
        if (showProgressBar) {
            val content = if (TextUtils.isEmpty(msg)) "请稍后" else msg
            if (netRequestListener != null) netRequestListener.showProgressDialog(content)
        }
    }

    override fun onAfter(id: Int) {
        super.onAfter(id)
        if (showProgressBar) {
            if (netRequestListener != null) netRequestListener.hideProgressDialog()
        }
    }

    override fun onResponse(response: String?, id: Int) {
        AppLog.d(Tag, response)
        if (context is BaseActivity && !(context as BaseActivity).isActivityValid) {
            //activity 销毁就不返回数据了
            return
        }
        nsParser.parse(response, clazz, this)
    }

    override fun onError(call: Call?, e: Exception?, id: Int) {
        if (e != null) {
            e.printStackTrace()
            Log.e(Tag, "网络请求故障")
        }
        if (netRequestListener != null) netRequestListener.onLoadError()
        onFail(-2, "")
    }

    open fun onSuccess(t: T?) {}

    open fun onSuccess(t: List<T>, total: Int) {}

    open fun onFail(code: Int, msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            AppTips.showToast(context, msg)
        }
    }
}