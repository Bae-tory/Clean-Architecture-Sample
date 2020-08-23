package com.sungjae.portfolio.models

data class SampleModel(
    val entryTexts: ArrayList<String> = ArrayList(),
    val entryValues: ArrayList<Float?> = ArrayList(),
    val entryDrawableResources: ArrayList<Int> = ArrayList(),
    val maxEntryText: String? = "",
    val maxEntryValue: Float? = 0f
)