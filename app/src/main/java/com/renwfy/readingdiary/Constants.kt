package com.renwfy.readingdiary

/**
 * Created by LSD on 2017/11/11.
 */
object Constants {
    val BASE_FOLDER = "/ReadDay"
    var DEBUG: Boolean = BuildConfig.DEBUG
    val OS_TYPE = "android"

    var BASE_URL = if (DEBUG) "https://rike-api.moreless.io" else "https://rike-api.moreless.io"
}