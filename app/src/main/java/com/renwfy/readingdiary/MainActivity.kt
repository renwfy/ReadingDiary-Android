package com.renwfy.readingdiary

import com.renwfy.readingdiary.activity.CommonActivity

class MainActivity : CommonActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun onViewCreated() {
        super.onViewCreated()
    }
}
