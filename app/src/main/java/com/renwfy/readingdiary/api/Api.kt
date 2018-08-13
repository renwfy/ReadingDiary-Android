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
    fun getLessons(upadteData: String, callback: NSCallback<LessonEntity>) {
        val url = Constants.BASE_URL + "/v1/lessons?from=20180101&to=20190726&updated_at=0"
        var pamas = HashMap<String, String>()
        get(url, pamas, callback)
    }


    //获取点赞数
    fun getActivityStats(dateString: String, callback: NSCallback<StatsEntity>) {
        val url = Constants.BASE_URL + "/v1/lessons/" + dateString + "/activity_stats"
        var pamas = HashMap<String, String>()
        get(url, pamas, callback)
    }

    //添加点赞数
    fun addFavor(callback: NSCallback<SuccessComm>) {
        val url = Constants.BASE_URL + "/v1/lessons/" + "" + "/activity_stats"
        var pamas = HashMap<String, String>()
        put(url, pamas, callback)
    }

    //删除点赞数
    fun deleteFavor(callback: NSCallback<SuccessComm>) {
        val url = Constants.BASE_URL + "/v1/lessons/" + "" + "/activity_stats"
        var pamas = HashMap<String, String>()
        delete(url, pamas, callback)
    }

    //登陆
    fun login(callback: NSCallback<User>) {
        val url = Constants.BASE_URL + "/v1/login"
        var pamas = HashMap<String, String>()
        pamas["nation_code"] = "86"
        pamas["phone_number"] = "18662430879"
        pamas["password"] = "123456"
        post(url, pamas, callback)
    }

}