package com.renwfy.readingdiary.model

/**
 * Created by LSD on 2018/7/30.
 * Desc
 */
class User {

    /**
     * id : 5b3c22b261083300052ee476
     * email :
     * nickname : Eric
     * sex : 0
     * birthday : 0
     * email_verified : false
     * nation_code : 86
     * phone_number : 18662430879
     * avatar : http://pics.tide.moreless.io/rike_avatar/FqSJ27A6HjkL8NC31BVvOlSyJC2Z
     * wechat : {"access_token":"","openid":"","unionid":""}
     * facebook : {"uid":"","access_token":""}
     * weibo : {"access_token":"","uid":""}
     * qq : {"access_token":"","openid":"","unionid":""}
     * created_at : 1530667698
     * updated_at : 1530667733
     * wechat_mp_first_login_time : 0
     * is_pwd_set : true
     * comment_limit : false
     */

    var id: String? = null
    var email: String? = null
    var nickname: String? = null
    var sex: Int = 0
    var birthday: Int = 0
    var isEmail_verified: Boolean = false
    var nation_code: String? = null
    var phone_number: String? = null
    var avatar: String? = null
    var wechat: WechatEntity? = null
    var facebook: FacebookEntity? = null
    var weibo: WeiboEntity? = null
    var qq: QqEntity? = null
    var created_at: Int = 0
    var updated_at: Int = 0
    var wechat_mp_first_login_time: Int = 0
    var isIs_pwd_set: Boolean = false
    var isComment_limit: Boolean = false

    class WechatEntity {
        /**
         * access_token :
         * openid :
         * unionid :
         */

        var access_token: String? = null
        var openid: String? = null
        var unionid: String? = null
    }

    class FacebookEntity {
        /**
         * uid :
         * access_token :
         */

        var uid: String? = null
        var access_token: String? = null
    }

    class WeiboEntity {
        /**
         * access_token :
         * uid :
         */

        var access_token: String? = null
        var uid: String? = null
    }

    class QqEntity {
        /**
         * access_token :
         * openid :
         * unionid :
         */

        var access_token: String? = null
        var openid: String? = null
        var unionid: String? = null
    }
}
