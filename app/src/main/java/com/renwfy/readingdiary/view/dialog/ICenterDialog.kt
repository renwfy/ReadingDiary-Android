package com.renwfy.readingdiary.view.dialog

import android.content.Context
import android.content.Intent
import butterknife.OnClick
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.activity.DiaryActivity
import com.renwfy.readingdiary.activity.DiaryNotificationActivity

/**
 * Created by LSD on 2018/7/26.
 * Desc
 */
class ICenterDialog(context: Context) : AbsDialog(context, R.layout.dialog_icenter, R.style.bottomInDialog) {

    @OnClick(R.id.ivCloseDialog)
    fun doClose() {
        dismiss()
    }

    @OnClick(R.id.itemCalendar)
    fun itemCalendar() {
        context.startActivity(Intent(context, DiaryActivity::class.java))
    }

    @OnClick(R.id.itemTip)
    fun itemTip() {
        context.startActivity(Intent(context, DiaryNotificationActivity::class.java))
    }
}