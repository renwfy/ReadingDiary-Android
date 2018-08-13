package com.renwfy.readingdiary.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.renwfy.readingdiary.fragment.PageFragment
import com.renwfy.readingdiary.model.LessonEntity

/**
 * Created by LSD on 2018/7/21.
 * Desc
 */
class HomePageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var list: List<LessonEntity>? = null
    var pageHolder = HashMap<String, Fragment>()

    open fun setData(list: List<LessonEntity>) {
        if (list == null || list.size == 0) {
            return
        }
        this.list = list
        this.notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        if (pageHolder["${position}"] != null) {
            return pageHolder["${position}"]!!
        } else {
            var fragment = PageFragment.getInstance(getListItem(position))
            pageHolder["${position}"] = fragment
            return fragment
        }
    }

    fun getListItem(position: Int): LessonEntity? {
        return if (list == null) null else list!![position]
    }

    override fun getCount(): Int {
        return if (list == null) 0 else list!!.size
    }
}