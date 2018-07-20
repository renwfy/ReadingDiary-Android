package com.renwfy.lib

import android.app.Application
import android.text.TextUtils
import com.renwfy.lib.utils.AppLog
import com.renwfy.lib.utils.FileUtil

object NSApp {
    private var debug = false
    lateinit var appContext: Application
    private var opt: NSOptions? = null

    fun init(context: Application, options: NSOptions?) {
        appContext = context
        opt = options
        if (options != null && !TextUtils.isEmpty(options!!.CACHE_PATH)) {
            FileUtil.CACHE_BASE = options!!.CACHE_PATH!!
        } else {
            FileUtil.CACHE_BASE = "/NS_APP"
        }
    }

    fun getContext(): Application {
        return appContext
    }

    fun setDebug(debug: Boolean) {
        NSApp.debug = debug
        AppLog.setLogSwitcher(debug)
    }

    fun isDebug(): Boolean {
        return debug
    }

    fun getNSOpions(): NSOptions? {
        return opt
    }
}