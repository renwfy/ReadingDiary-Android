package com.renwfy.readingdiary.activity

import android.support.v4.app.FragmentManager
import butterknife.OnClick
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.fragment.PageFragment
import com.renwfy.readingdiary.model.LessonEntity

/**
 * Created by LSD on 2018/7/31.
 * Desc
 */
class DiaryDetailsActivity : CommonActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_diary_details
    }

    override fun onViewCreated() {
        super.onViewCreated()
        var data: LessonEntity = intent.getSerializableExtra("entity") as LessonEntity
        initFragment(data)
    }

    private fun initFragment(data: LessonEntity) {
        val manager: FragmentManager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.framelayout, PageFragment.getInstance(data))
        transaction.commit()
    }

    @OnClick(R.id.ivBack)
    fun back() {
        finish()
    }
}