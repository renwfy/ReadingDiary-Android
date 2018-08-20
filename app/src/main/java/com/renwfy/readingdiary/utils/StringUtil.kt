package com.renwfy.readingdiary.utils

import java.text.SimpleDateFormat
import java.util.regex.Pattern

/**
 * Created by LSD on 2018/7/26.
 * Desc
 */
object StringUtil {
    fun toDateStringCN(dateStr: String): String {
        try {
            //检查时间正确性
            val format = SimpleDateFormat("yyyyMMdd")
            format.parse(dateStr)

            val year = dateStr.substring(0, 4);
            val month = dateStr.substring(4, 6).replace("0", "")
            val day = dateStr.substring(6, 8).replace("0", "")

            var returnStr = StringBuffer()
            for (item in month.toCharArray()) {
                returnStr.append(charToNum(item))
            }
            returnStr.append("月")
            for (item in day.toCharArray()) {
                returnStr.append(charToNum(item))
            }

            return returnStr.toString()
        } catch (e: Exception) {
            return ""
        }
    }

    fun charToNum(x: Char): Char {
        val stringChnNames = "零一二三四五六七八九"
        val stringNumNames = "0123456789"
        return stringChnNames[stringNumNames.indexOf(x)]
    }

    fun intToNum(x: Int): String {
        val stringNames = arrayOf("零", "一", "二", "三", "四", "五", "六", "七", "八", "九")
        val intNames = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        return stringNames[intNames.indexOf(x)]
    }

    //转竖直显示的字符串
    fun toVerticalString(str: String): String {
        val buffer = StringBuffer()
        for (i in 0 until str.length) {
            buffer.append(str[i])
            buffer.append("\n")
        }
        return buffer.toString()
    }

    fun isPhoneNumber(phone: String): Boolean {
        val p = Pattern.compile("^1\\d{10}$")
        return p.matcher(phone).matches()
    }

}