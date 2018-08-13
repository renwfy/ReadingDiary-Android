package com.renwfy.readingdiary.utils

import android.os.Handler

class TimerUtil(private val handler: Handler) {

    private val task = Runnable {
        stopTimer()
        handler.sendEmptyMessage(0)
    }

    fun startTimer(time: Long): TimerUtil {
        stopTimer()
        handler.postDelayed(task, time)
        return this
    }

    fun stopTimer(): TimerUtil {
        handler.removeCallbacks(task)
        return this
    }

}
