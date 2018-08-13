package com.renwfy.lib.fragment

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by LSD on 2018/7/21.
 * Desc
 */
abstract class BaseFragment : Fragment() {
    lateinit var mContext: Activity
    internal var rootView: View? =null //缓存Fragment view
    var mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (rootView == null) {
            rootView = inflater.inflate(getFrameLayout(), null)
            onViewCreated()
        }
        //缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        if (rootView!!.parent != null) {
            val parent = rootView!!.parent as ViewGroup
            parent.removeView(rootView)
        }
        return rootView!!
    }

    override fun onResume() {
        super.onResume()
        val displayTitle = getDisplayTitle()
        if (!TextUtils.isEmpty(displayTitle)) {
        } else {
        }
    }

    override fun onPause() {
        super.onPause()
        val displayTitle = getDisplayTitle()
        if (!TextUtils.isEmpty(displayTitle)) {
        } else {
        }
    }

    //用于友盟统计的描述
    protected fun getDisplayTitle(): String {
        return ""
    }

    protected abstract fun getFrameLayout(): Int

    open protected fun onViewCreated() {}
}