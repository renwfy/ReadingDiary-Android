package com.renwfy.lib.utils

import android.util.Log

object AppLog {
    internal var isPrintLog = true

    fun d(tag: String?, meg: String?) {
        if (tag != null && meg != null && isPrintLog)
            Log.d(tag, meg)
    }

    fun e(tag: String?, meg: String?) {
        if (tag != null && meg != null && isPrintLog)
            Log.e(tag, meg)
    }

    fun i(tag: String?, meg: String?) {
        if (tag != null && meg != null && isPrintLog)
            Log.i(tag, meg)
    }

    fun v(tag: String?, meg: String?) {
        if (tag != null && meg != null && isPrintLog)
            Log.v(tag, meg)
    }

    fun w(tag: String?, meg: String?) {
        if (tag != null && meg != null && isPrintLog)
            Log.w(tag, meg)
    }

    fun getLogStatus(): Boolean {
        return isPrintLog
    }

    fun setLogSwitcher(paramBoolean: Boolean) {
        isPrintLog = paramBoolean
    }
}