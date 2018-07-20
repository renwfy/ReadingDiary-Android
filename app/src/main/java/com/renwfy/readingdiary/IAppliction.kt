package com.renwfy.readingdiary

import android.app.Application
import android.content.Context
import com.renwfy.lib.NSApp
import com.renwfy.lib.NSOptions
import com.renwfy.lib.activity.ActivityManager
import com.renwfy.lib.net.NSRequestManager
import com.renwfy.readingdiary.model.User
import kotlin.properties.Delegates


/**
 * Created by LSD on 2017/11/11.
 */
class IAppliction : Application() {
    private lateinit var session: Session

    companion object {
        var instance: IAppliction by Delegates.notNull()

        fun getAppliction(): IAppliction {
            return instance
        }

        //退出
        fun exit() {
            ActivityManager.popAllActivity()
            System.exit(1)
        }
    }

    init {
        instance = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //MultiDex.install(this) //dex分包
    }


    override fun onCreate() {
        super.onCreate()
        session = Session.load()

        NSApp.init(this, sysOptions())
        NSApp.setDebug(Constants.DEBUG)
        NSRequestManager.init(this, Constants.BASE_URL)
    }

    //app参数配置
    fun sysOptions(): NSOptions {
        var options = NSOptions()
        options.OS_TYPE = Constants.OS_TYPE
        options.OS_VERSION = BuildConfig.VERSION_CODE
        options.CACHE_PATH = Constants.BASE_FOLDER
        return options
    }

    fun getUser(): User? {
        return session.getUser()
    }
}