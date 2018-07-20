package com.renwfy.readingdiary

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import com.renwfy.readingdiary.model.User

/**
 * Created by LSD on 2017/12/3.
 */
class Session {
    private var user: User? = null

    companion object {
        private var SESSION = ""

        /**
         * 加载到内存
         */
        fun load(): Session {
            SESSION = if (Constants.DEBUG) "_SESSION" else "_ONLINE_SESSION"
            val sp = IAppliction.instance.getSharedPreferences(SESSION, Context.MODE_PRIVATE)
            val info = sp.getString(SESSION, "")
            var result: Session? = null
            if (TextUtils.isEmpty(info)) {
                result = Session()
            } else {
                result = Gson().fromJson<Session>(info, Session::class.java)
            }
            return result!!
        }
    }

    /**
     * 持久化
     */
    fun save() {
        val sp = IAppliction.instance.getSharedPreferences(SESSION, Context.MODE_PRIVATE)
        val editor = sp.edit()
        val session = Gson().toJson(this)
        editor.putString(SESSION, session)
        editor.commit()
    }

    fun saveUser(user: User) {
        this.user = user
        save()
    }

    fun getUser(): User? {
        return user
    }

    fun logout() {
        user = null
        save()
    }
}