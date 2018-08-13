package com.renwfy.readingdiary.activity

import android.content.Intent
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.renwfy.lib.net.NSCallback
import com.renwfy.readingdiary.MainActivity
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.api.Api
import com.renwfy.readingdiary.data.DataFactory
import com.renwfy.readingdiary.model.LessonEntity
import java.util.*

/**
 * Created by LSD on 2018/7/30.
 * Desc
 */
class SplashActivity : CommonActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_splash
    }

    override fun onViewCreated() {
        super.onViewCreated()
        preLoad()
    }

    fun preLoad() {
        Api.getLessons("", object : NSCallback<LessonEntity>(mActivity, LessonEntity::class.java) {
            override fun onResponse(response: String?, id: Int) {
                if (!TextUtils.isEmpty(response)) {
                    val type = object : TypeToken<ArrayList<LessonEntity>>() {}.type
                    val rs = Gson().fromJson<List<LessonEntity>>(response, type)
                    DataFactory.lessonData = rs

                    startActivity(Intent(mActivity, MainActivity::class.java))
                    finish()
                }
            }
        })
    }
}