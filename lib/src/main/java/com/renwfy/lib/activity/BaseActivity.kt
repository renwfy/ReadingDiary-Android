package com.renwfy.lib.activity

import android.support.v7.app.AppCompatActivity
import com.renwfy.lib.net.NSCallback

/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
open abstract class BaseActivity : AppCompatActivity(), NSCallback.NetRequestListener {
    var isActivityValid: Boolean = true //是否可用
}