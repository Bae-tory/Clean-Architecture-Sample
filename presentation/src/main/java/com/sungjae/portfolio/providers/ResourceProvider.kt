package com.sungjae.portfolio.providers

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes res: Int): String

}