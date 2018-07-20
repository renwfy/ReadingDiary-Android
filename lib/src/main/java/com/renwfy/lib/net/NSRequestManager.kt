package com.renwfy.lib.net

import android.content.Context
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.https.HttpsUtils
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
class NSRequestManager {
    companion object {
        private var INSTANCE: NSRequestManager? = null
        private var ctx: Context? = null

        fun init(context: Context, baseUrl: String): NSRequestManager {
            INSTANCE = NSRequestManager()
            ctx = context
            NSHttpClent.init(baseUrl)
            InitOkHttpClient()
            return INSTANCE as NSRequestManager
        }

        fun InitOkHttpClient() {
            //ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
            //https
            val sslParams = HttpsUtils.getSslSocketFactory(null, null, null)//证书的inputstream,本地证书的inputstream,本地证书的密码
            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
                    .readTimeout((60 * 1000).toLong(), TimeUnit.MILLISECONDS)
                    //.addInterceptor(new LoggerInterceptor("TAG"))
                    //.cookieJar(cookieJar)
                    .hostnameVerifier { hostname, session -> true }
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build()
            OkHttpUtils.initClient(okHttpClient)
        }

        fun getInstance(): NSRequestManager {
            if (INSTANCE == null) {
                throw RuntimeException("Request 未初始化!")
            }
            return INSTANCE!!
        }

        fun getContext(): Context {
            return ctx!!
        }
    }
}