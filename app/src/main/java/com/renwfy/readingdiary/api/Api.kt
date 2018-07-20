package com.renwfy.readingdiary.api

import com.renwfy.lib.net.NSCallback
import com.renwfy.lib.net.NSHttpClent
import com.renwfy.readingdiary.Constants
import com.renwfy.readingdiary.model.BannerEntity
import com.renwfy.readingdiary.model.VideoConfigEntity

/**
 * Created by LSD on 2018/7/18.
 * Desc
 */
object Api : NSHttpClent() {
    fun tvConfig(callback: NSCallback<VideoConfigEntity>) {
        val url = Constants.BASE_URL + "/itv/video/config"
        var pamas = HashMap<String, String>()
        get(url, pamas, callback)
    }

    fun tvBanner(callback: NSCallback<BannerEntity>) {
        val url = Constants.BASE_URL + "/itv/video/banner"
        val params = java.util.HashMap<String, String>()
        params["source"] = "80stv"
        get(url, params, callback)
    }
}