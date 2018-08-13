package com.renwfy.readingdiary.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import butterknife.ButterKnife
import butterknife.Unbinder
import com.renwfy.readingdiary.R

/**
 * Created by LSD on 2018/7/19.
 * Desc
 */
abstract class CommonActivity : AbstractActivity() {
    lateinit var container: LinearLayout
    internal var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getFrameLayout())

        initFrameLayout()
        val viewId = getContentView()
        if (0 != viewId) {
            val view = View.inflate(mActivity, viewId, null)
            setView(view)
            unbinder = ButterKnife.bind(this,view)
        }
        onViewCreated()
    }

    override fun onDestroy() {
        if(unbinder != null){
            unbinder!!.unbind()
        }
        super.onDestroy()
    }

    //FrameLayout
    protected fun getFrameLayout(): Int {
        return R.layout.activity_common
    }

    private fun initFrameLayout() {
        container = findViewById(R.id.mContainer)
    }

    @LayoutRes
    protected abstract fun getContentView(): Int

    open protected fun onViewCreated() {}

    protected fun setView(view: View) {
        val LAYOUT_PARAMS = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        container.removeAllViews()
        container.addView(view, LAYOUT_PARAMS)
    }
}