package com.renwfy.lib.utils

/**
 * Created by LSD on 2018/7/19.
 * Desc  单例demo
 */
class TET {
    private object holder {
        val INSTANCE = TET();
    }

    companion object {
        fun getInstance() = holder.INSTANCE
    }

    fun show() {
    }
}