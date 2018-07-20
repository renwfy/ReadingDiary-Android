package com.renwfy.lib.activity

import android.app.Activity
import android.util.Log
import com.renwfy.lib.utils.AppLog
import java.util.*
import kotlin.properties.Delegates

object ActivityManager {
    val activityStack: Stack<Activity> = Stack()

    //退出栈顶Activity
    fun popActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            //在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
            activityStack.remove(activity)
            activity = null
        }
    }

    //获得当前栈顶Activity
    fun currentActivity(): Activity? {
        var activity: Activity? = null
        if (!activityStack.empty())
            activity = activityStack.lastElement()
        return activity
    }

    //可以返回
    fun popBackStackImmediate(): Boolean {
        return activityStack.size > 1
    }

    //将当前Activity推入栈中
    fun pushActivity(activity: Activity) {
        activityStack.add(activity)
    }

    //退出栈中所有Activity
    fun popAllActivity() {
        while (!activityStack.empty()) {
            val activity = currentActivity() ?: break
            activity.finish()
            popActivity(activity)
        }
        Log.e("activity销毁", "activity销毁")
    }
}