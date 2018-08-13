package com.renwfy.readingdiary.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.adapter.DairyRecyclerAdapter
import com.renwfy.readingdiary.data.DataFactory
import com.renwfy.readingdiary.model.DiaryEntity
import com.renwfy.readingdiary.model.LessonEntity
import com.renwfy.readingdiary.utils.StringUtil
import java.util.*

/**
 * Created by LSD on 2018/7/30.
 * Desc
 */
class DiaryActivity : CommonActivity() {
    @BindView(R.id.llContent)
    lateinit var llContent: LinearLayout
    @BindView(R.id.tvLastDairy)
    lateinit var tvLastDairy: TextView

    var list: MutableList<DiaryEntity> = ArrayList()

    override fun getContentView(): Int {
        return R.layout.activity_diary
    }

    override fun onViewCreated() {
        super.onViewCreated()


        if (DataFactory.lessonData != null) {
            var lastItem = DataFactory.lessonData!![DataFactory.lessonData!!.size - 1]
            var dateString = "${lastItem.date_by_day}"
            val year = dateString.substring(0, 4).toInt()
            val month = dateString.substring(4, 6).toInt()
            for (i in 0 until month) {
                var diary = DiaryEntity()
                diary.dateString = StringUtil.intToNum(i + 1) + "月"
                list.add(diary)
            }
            val data = DataFactory.lessonData!!
            for (item in data) {
                var tMonth = ((item.date_by_day - (year * 10000)) / 100)
                list.get(tMonth - 1).data.add(item)
            }
            list.reverse()//颠倒顺序
            addContentLayout(list)
            tvLastDairy.text = "${year - 1}年已经过去了，我也很怀念它。"
        }
    }


    private fun addContentLayout(list: MutableList<DiaryEntity>) {
        for (item in list) {
            val view = LayoutInflater.from(mActivity).inflate(R.layout.layout_m_diary_item, null)
            val recyclerView = view.findViewById(R.id.recylerView) as RecyclerView
            val textView = view.findViewById(R.id.tvMonth) as TextView
            textView.text = item.dateString
            val adapter = DairyRecyclerAdapter(mActivity)
            recyclerView.setLayoutManager(LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter)
            adapter.setData(item.data)
            llContent.addView(view)
        }
    }

    @OnClick(R.id.ivBack)
    fun back(){
        finish()
    }

}