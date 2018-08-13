package com.renwfy.readingdiary.activity

import butterknife.OnClick
import com.renwfy.readingdiary.R

/**
 * Created by LSD on 2018/8/3.
 * Desc
 */
class AboutActivity : CommonActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_about
    }

    @OnClick(R.id.ivBack)
    fun back() {
        finish()
    }
}