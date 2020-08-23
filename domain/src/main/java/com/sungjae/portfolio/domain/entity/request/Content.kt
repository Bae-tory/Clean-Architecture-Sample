package com.sungjae.portfolio.domain.entity.request

data class Content(
    val query: String,
    val contentItems: List<ContentItem>
)

data class ContentItem(
    val image: String = "",
    val actor: String = "",
    val description: String = "",
    val title: String = "",
    val link: String = ""
)
