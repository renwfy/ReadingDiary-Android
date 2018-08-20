package com.renwfy.readingdiary.activity

import android.content.Intent
import android.text.TextUtils
import android.widget.EditText
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.OnClick
import com.renwfy.lib.net.NSCallback
import com.renwfy.lib.utils.AppTips
import com.renwfy.readingdiary.IAppliction
import com.renwfy.readingdiary.MainActivity
import com.renwfy.readingdiary.R
import com.renwfy.readingdiary.api.Api
import com.renwfy.readingdiary.data.DataFactory
import com.renwfy.readingdiary.model.LessonEntity
import com.renwfy.readingdiary.model.User
import com.renwfy.readingdiary.utils.StringUtil

/**
 * Created by LSD on 2018/8/3.
 * Desc
 */
class LoginActivity : CommonActivity() {
    @BindView(R.id.etPhone)
    lateinit var etPhone: EditText
    @BindView(R.id.etPass)
    lateinit var etPass: EditText

    override fun getContentView(): Int {
        return R.layout.activity_login
    }

    @OnClick(R.id.btLogin)
    fun login() {
        val phone = etPhone.text.toString()
        val pass = etPass.text.toString()
        if (TextUtils.isEmpty(phone)) {
            AppTips.showToast("请输入手机号")
            return
        }
        if (!StringUtil.isPhoneNumber(phone)) {
            AppTips.showToast("请输入正确的手机号")
            return
        }
        if (TextUtils.isEmpty(pass)) {
            AppTips.showToast("请输入密码")
            return
        }

        Api.login(phone, pass, object : NSCallback<User>(mActivity, User::class.java) {
            override fun onSuccess(t: User?) {
                if (t != null) {
                    IAppliction.instance.saveUser(t)
                }
                finish()
            }
        })
    }

    @OnClick(R.id.ivBack)
    fun back() {
        finish()
    }
}