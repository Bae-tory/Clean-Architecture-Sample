package com.sungjae.portfolio.extensions

import android.app.Activity
import android.content.Intent

inline fun Intent.takeIfResultOK(resultCode: Int, block: (Intent) -> Unit) {
    if (resultCode != Activity.RESULT_OK) return
    block(this)
}