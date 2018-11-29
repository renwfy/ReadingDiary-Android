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

    //收藏状态
    fun likeStatus(lessonId: String, userId: String, callback: NSCallback<LikeEntity>) {
        val url = "/v1/lessons/favor"
        var pamas = HashMap<String, String>()
        pamas["lessonId"] = lessonId
        pamas["userId"] = userId
        get(url, pamas, callback)
    }

    //添加删除点赞
    fun doFavor(lessonId: String, userId: String, date_by_day: String, status: String, callback: NSCallback<LikeEntity>) {
        val url = "/v1/lessons/favor"
        var pamas = HashMap<String, String>()
        pamas["lessonId"] = lessonId
        pamas["userId"] = userId
        pamas["date_by_day"] = date_by_day
        pamas["status"] = status
        post(url, pamas, callback)
    }


    //登陆
    fun login(phone: String, pass: String, callback: NSCallback<User>) {
        val url = "/v1/user"
        var pamas = HashMap<String, String>()
        pamas["phone"] = phone
        pamas["pass"] = pass
        post(url, pamas, callback)
    }


    //评论
    fun comentList(userId: String, lesson_id: String, start: Int, size: Int, callback: NSCallback<CommentEntity>) {
        val url = "/v1/comments"
        var pamas = HashMap<String, String>()
        pamas["userId"] = userId
        pamas["lesson_id"] = lesson_id
        pamas["start"] = "$start"
        pamas["size"] = "$size"
        get(url, pamas, callback)
    }


    //评论点赞
    fun comentLike(userId: String, comment_id: String, status: Boolean, callback: NSCallback<SuccessComm>) {
        val url = "/v1/comment_like"
        var pamas = HashMap<String, String>()
        pamas["userId"] = userId
        pamas["comment_id"] = comment_id
        pamas["status"] = "$status"
        post(url, pamas, callback)
    }
}