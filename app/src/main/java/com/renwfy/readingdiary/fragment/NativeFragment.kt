package com.renwfy.readingdiary.fragment

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import butterknife.ButterKnife
import com.renwfy.lib.fragment.BaseFragment
import com.renwfy.readingdiary.R

/**
 * Created by LSD on 2018/7/21.
 * Desc
 */
abstract class NativeFragment : BaseFragment() {
    lateinit var container: LinearLayout

    override fun getFrameLayout(): Int {
        return R.layout.fragment_native
    }

    override fun onViewCreated() {
        initFrameLayout()
        val viewId = getContentView()
        if (viewId != 0) {
            val view = View.inflate(mContext, viewId, null)
            ButterKnife.bind(this, view)
            setView(view)
        }
    }

    private fun initFrameLayout() {
        container = rootView!!.findViewById(R.id.ll_container)
    }

    abstract fun getContentView(): Int

    private fun setView(view: View) {
        val LAYOUT_PARAMS = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        container.removeAllViews()
        container.addView(view, LAYOUT_PARAMS)
    }
}