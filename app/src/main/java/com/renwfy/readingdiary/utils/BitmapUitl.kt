package com.renwfy.readingdiary.utils

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

/**
 * Created by LSD on 2018/8/6.
 * Desc
 */
object BitmapUitl {
    fun bmpToByteArray(bmp: Bitmap, needRecycle: Boolean): ByteArray {
        val output = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output)
        if (needRecycle) {
            bmp.recycle()
        }

        val result = output.toByteArray()
        try {
            output.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }
}