package com.renwfy.readingdiary.api

import com.renwfy.lib.net.NSCallback
import com.renwfy.lib.net.NSHttpClent
import com.renwfy.readingdiary.Constants
import com.renwfy.readingdiary.model.*

/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
object Api : NSHttpClent() {

    //课文章
    fun getLessons(callback: NSCallback<LessonEntity>) {
        val url = "/v1/lessons"
        var pamas = HashMap<String, String>()
        get(url, pamas, callback)
    }


    //获取点赞数
    fun getActivityStats(dateString: String, callback: NSCallback<StatsEntity>) {
        val url = "/v1/lessons/activity_stats"
        var pamas = HashMap<String, String>()
        pamas["date_by_day"] = dateString
        get(url, pamas, callback)
    }

    //添加点赞数
    fun addFavor(callback: NSCallback<SuccessComm>) {
        val url = "/v1/lessons/" + "" + "/activity_stats"
        var pamas = HashMap<String, String>()
        put(url, pamas, callback)
    }

    //删除点赞数
    fun deleteFavor(callback: NSCallback<SuccessComm>) {
        val url = "/v1/lessons/" + "" + "/activity_stats"
        var pamas = HashMap<String, String>()
        delete(url, pamas, callback)
    }

    //收藏状态
    fun likeStatus(lessonId: String, userId: String, callback: NSCallback<SuccessComm>) {
        val url = "/v1/lessons/like"
        var pamas = HashMap<String, String>()
        pamas["lessonId"] = lessonId
        pamas["userId"] = userId
        get(url, pamas, callback)
    }

    //登陆
    fun login(phone: String, pass: String, callback: NSCallback<User>) {
        val url = "/v1/user"
        var pamas = HashMap<String, String>()
        pamas["phone"] = phone
        pamas["pass"] = pass
        post(url, pamas, callback)
    }

}