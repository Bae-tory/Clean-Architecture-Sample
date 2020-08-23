package com.sungjae.portfolio.components

import android.content.res.ColorStateList
import android.graphics.Color
import com.sungjae.portfolio.extensions.trimBlank

object ConverterUtil {
    @JvmStatic
    fun trimBlank(rawString: String?) = rawString?.trimBlank()

    @JvmStatic
    fun parseColor(colorCode: String?): ColorStateList {
        return ColorStateList.valueOf(Color.parseColor("#${colorCode}"))
    }

    @JvmStatic
    fun toString(res: Any?): String? = res.toString()

}