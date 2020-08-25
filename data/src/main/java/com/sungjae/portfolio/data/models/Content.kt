package com.sungjae.portfolio.data.models

import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.entity.request.ContentEntityItem

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

fun Content.toEntity(): ContentEntity =
    ContentEntity(
        query = query,
        contentEntityItems = contentItems.map {
            ContentEntityItem(
                image = it.image,
                actor = it.actor,
                description = it.description,
                title = it.title,
                link = it.link
            )
        }
    )

