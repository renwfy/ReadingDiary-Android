package com.renwfy.readingdiary.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import com.renwfy.lib.activity.ActivityManager
import com.renwfy.lib.activity.BaseActivity
import com.renwfy.lib.utils.AppTips
import com.renwfy.readingdiary.Constants
import com.renwfy.readingdiary.IAppliction

/**
 * Created by LSD on 2018/7/19.
 * Desc
 */
open class AbstractActivity : BaseActivity() {
    lateinit var mActivity: AbstractActivity
    private var _progressDialog: ProgressDialog? = null
    protected var mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this;
        ActivityManager.pushActivity(this)
        isActivityValid = true
    }

    override fun onResume() {
        super.onResume()
        if (!Constants.DEBUG) {
            //友盟统计
        }
    }

    override fun onPause() {
        super.onPause()
        if (!Constants.DEBUG) {
            //友盟统计
        }
    }

    override fun onDestroy() {
        ActivityManager.popActivity(this)
        isActivityValid = false
        super.onDestroy()
    }

    override fun showProgressDialog(msg: String) {
        showProgressDialog(msg, true)
    }

    fun showProgressDialog(msg: String, canCancle: Boolean) {
        try {
            mHandler.post {
                if (_progressDialog == null) {
                    _progressDialog = ProgressDialog(mActivity)
                    _progressDialog!!.setCancelable(canCancle)
                }
                if (!_progressDialog!!.isShowing()) {
                    _progressDialog!!.show()
                }
            }
        } catch (e: Exception) {
        }
    }

    override fun hideProgressDialog() {
        try {
            mHandler.post {
                if (_progressDialog != null) {
                    _progressDialog!!.dismiss()
                }
            }
        } catch (e: Exception) {
        }
    }

    override fun onLoadError() {
    }

    //Back退出
    private var isfirstTime = true

    override fun onBackPressed() {
        if (ActivityManager.popBackStackImmediate()) {
            super.onBackPressed()
        } else {
            if (isfirstTime) {
                AppTips.showToast(mActivity, "再按一次退出程序...")
                isfirstTime = false
                mHandler.postDelayed({ isfirstTime = true }, 3000)
            } else {
                IAppliction.exit()// 否则退出程序
            }
        }
    }
}