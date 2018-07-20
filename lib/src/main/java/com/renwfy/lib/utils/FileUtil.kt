package com.renwfy.lib.utils

import java.io.File

object FileUtil {
    var CACHE_BASE = ""

    fun getCacheFoder(): String {
        if (!FileUtil.isSDExist()) {
            return ""
        }
        val folder = File(getFolderPath())
        if (!folder.exists()) {
            folder.mkdirs()
        }
        return folder.absolutePath
    }

    private fun getFolderPath(): String {
        return android.os.Environment.getExternalStorageDirectory().toString() + CACHE_BASE
    }

    fun isSDExist(): Boolean {
        return android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED
    }
}