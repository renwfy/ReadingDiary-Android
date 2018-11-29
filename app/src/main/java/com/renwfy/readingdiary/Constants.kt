package com.renwfy.readingdiary

/**
 * Created by LSD on 2017/11/11.
 */
object Constants {
    val BASE_FOLDER = "/Lessons"
    var DEBUG: Boolean = BuildConfig.DEBUG
    val OS_TYPE = "android"

    var BASE_URL = if (DEBUG) "http://192.168.41.183:3004/api" else "https://lesson.fiz.ink/api"//10.0.2.2
}