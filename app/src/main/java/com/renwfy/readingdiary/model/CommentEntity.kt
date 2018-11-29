package com.renwfy.readingdiary.model

/**
 * Created by LSD on 2018/9/14.
 * Desc
 */
class CommentEntity {
    var id = ""
    var content = ""
    var user: User? = null
    var like_count = 0
    var sub_comment_count = 0
    var lesson_id = ""
    var my_like = false
}