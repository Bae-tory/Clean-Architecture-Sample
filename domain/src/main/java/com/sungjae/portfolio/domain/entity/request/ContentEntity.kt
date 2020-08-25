package com.sungjae.portfolio.domain.entity.request

data class ContentEntity(
    val query: String,
    val contentEntityItems: List<ContentEntityItem>
)

data class ContentEntityItem(
    val image: String = "",
    val actor: String = "",
    val description: String = "",
    val title: String = "",
    val link: String = ""
)