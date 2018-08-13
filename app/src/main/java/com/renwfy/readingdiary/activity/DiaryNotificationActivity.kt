package com.renwfy.readingdiary.activity

import android.app.AlarmManager
import android.content.Context
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import butterknife.BindView
import butterknife.OnCheckedChanged
import butterknife.OnClick
import cn.qqtheme.framework.picker.TimePicker
import cn.qqtheme.framework.util.ConvertUtils
import com.orhanobut.hawk.Hawk
import com.renwfy.lib.utils.AppLog
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.MainActivity
import android.app.PendingIntent
import android.support.v4.view.accessibility.AccessibilityEventCompat.setAction
import android.content.Intent
import java.util.*


/**
 * Created by LSD on 2018/8/2.
 * Desc
 */
class DiaryNotificationActivity : CommonActivity() {
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent
    @BindView(R.id.mSwitch)
    lateinit var mSwitch: Switch
    @BindView(R.id.tvTipTime)
    lateinit var tvTipTime: TextView

    var mHour = 22
    var mMinute = 0
    var switch = false

    override fun getContentView(): Int {
        return R.layout.activity_diary_notification
    }

    override fun onViewCreated() {
        super.onViewCreated()
        init()
        initAlarm()
    }

    fun init() {
        mHour = Hawk.get("tipHour", 22)
        mMinute = Hawk.get("tipMinute", 0)
        switch = Hawk.get("tipSwitch", false)
        mSwitch.isSelected = switch
        var text = (if (mHour > 9) "${mHour}" else "0${mHour}") + ":" + (if (mMinute > 9) "${mMinute}" else "0${mMinute}")
        tvTipTime.text = text
    }

    fun initAlarm() {
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent()
        intent.action = "com.renwfy.dairy.Notification"
        pendingIntent = PendingIntent.getBroadcast(mActivity, 21, intent, 0)
    }


    @OnCheckedChanged(R.id.mSwitch)
    fun onSwitchChange(isChecked: Boolean) {
        if (!isChecked) {
            alarmManager.cancel(pendingIntent)
        } else {
            val now = System.currentTimeMillis()
            val date = Date(now)
            date.hours = Hawk.get("tipHour")
            date.minutes = Hawk.get("tipMinute")
            var nextDay = date.time - now + (24 * 60 * 60 * 1000)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, now, nextDay, pendingIntent)
        }
    }

    @OnClick(R.id.llSetTime)
    fun showTimePicker() {
        val picker = TimePicker(this, TimePicker.HOUR_24)
        picker.setUseWeight(false)
        picker.setTextColor(resources.getColor(R.color.black))
        picker.setCancelTextColor(resources.getColor(R.color.black))
        picker.setSubmitTextColor(resources.getColor(R.color.black))
        picker.setLabelTextColor(resources.getColor(R.color.black))
        picker.setDividerColor(resources.getColor(R.color.line))
        picker.setCycleDisable(false)
        picker.setLabel("", "")
        picker.setRangeStart(0, 0)
        picker.setRangeEnd(23, 59)
        picker.setSelectedItem(mHour, mMinute)
        picker.setTopLineVisible(false)
        picker.setTextPadding(ConvertUtils.toPx(this, 15f))
        picker.setOnTimePickListener { hour, minute ->
            tvTipTime.text = hour + ":" + minute
            mHour = hour.toInt()
            mMinute = minute.toInt()
            Hawk.put("tipHour", hour.toInt())
            Hawk.put("tipMinute", minute.toInt())
        }
        picker.show()
    }

    @OnClick(R.id.ivBack)
    fun back() {
        finish()
    }
}