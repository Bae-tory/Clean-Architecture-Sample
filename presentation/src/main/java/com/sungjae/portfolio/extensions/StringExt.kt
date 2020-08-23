package com.sungjae.portfolio.extensions

fun String?.trimBlank() = this?.replace(" ", "\u00A0")
