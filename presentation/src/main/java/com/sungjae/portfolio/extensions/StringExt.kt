package com.sungjae.portfolio.extensions

import androidx.core.text.HtmlCompat

fun String?.trimBlank() = this?.replace(" ", "\u00A0")

fun String.fromHtml() =
    HtmlCompat.fromHtml(
        this,
        HtmlCompat.FROM_HTML_MODE_COMPACT
    )