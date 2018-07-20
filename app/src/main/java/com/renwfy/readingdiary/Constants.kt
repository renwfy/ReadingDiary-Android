package com.renwfy.readingdiary

/**
 * Created by LSD on 2017/11/11.
 */
object Constants {
    val BASE_FOLDER = "/ReadDay"
    var DEBUG: Boolean = BuildConfig.DEBUG
    val OS_TYPE = "android"

    var QINIU_HOST = "http://oac4ul6pe.bkt.clouddn.com/"//七牛地址

    var HOST = if (DEBUG) "http://renwfy.top" else "http://renwfy.top"
    var BASE_URL = HOST + "/api"
    var WEB_HOST = HOST + "/webapp"
}