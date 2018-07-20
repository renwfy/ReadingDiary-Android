package com.renwfy.lib.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.renwfy.lib.NSApp

object AppTips {
    var lastTime: Long = 0

    fun showToast(content: String) {
        showToast(NSApp.getContext(), content)
    }

    fun showToast(context: Context, content: String) {
        val curTime = System.currentTimeMillis()
        if (curTime - lastTime < 1000) {
            lastTime = curTime
            return
        }
        if (TextUtils.isEmpty(content)) {
            return
        }
        val toast = Toast.makeText(context, content, Toast.LENGTH_SHORT)
        toast.getView().getBackground().setAlpha(255);//设置透明度
        toast.show()
    }
}