package com.renwfy.readingdiary.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.renwfy.readingdiary.MainActivity
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.activity.SplashActivity
import java.util.*


/**
 * Created by LSD on 18/9/12.
 */
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if ("com.renwfy.dairy.Notification" == intent.action) {
            notification(context)
        }
        if ("android.intent.action.TIME_TICK" == intent.action) {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)
            if (hour == 10 && minute == 30) {
                notification(context)
            }
        }
    }


    fun notification(context: Context) {
        //实例化通知管理器
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //实例化通知
        val builder = NotificationCompat.Builder(context)
        builder.setDefaults(NotificationCompat.DEFAULT_ALL)//默认的通知手机声音
        builder.setContentTitle("日课")
        builder.setSmallIcon(R.drawable.ic_rike)
        builder.setContentText("一日一读，一期一会。")
        var target: Intent
        if (MainActivity.FRONT) {
            target = Intent(context, MainActivity::class.java)
        } else {
            target = Intent(context, SplashActivity::class.java)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, 22, target, 0)
        builder.setContentIntent(pendingIntent)
        val notification = builder.build()
        //发送通知
        notificationManager.notify(0x104, notification)
    }
}
