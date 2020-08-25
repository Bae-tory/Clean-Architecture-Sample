package com.sungjae.portfolio.remote.models

import com.google.gson.annotations.SerializedName
import com.sungjae.portfolio.data.models.Content
import com.sungjae.portfolio.data.models.ContentItem
import com.sungjae.portfolio.domain.entity.request.ContentEntity
import com.sungjae.portfolio.domain.entity.request.ContentEntityItem

data class ContentResponse(
    var query: String,
    @SerializedName("items")
    val contentResponseItems: List<ContentResponseItem>
)

data class ContentResponseItem(
    @SerializedName("image")
    val image: String = "",
    @SerializedName("subtitle")
    val subtitle: String = "",
    @SerializedName("pubdate")
    val pubDate: String = "",
    @SerializedName("director")
    val director: String = "",
    @SerializedName("actor")
    val actor: String = "",
    @SerializedName("userRating")
    val userRating: Double = 0.00,

    @SerializedName("bloggerlink")
    val bloggerlink: String = "",
    @SerializedName("bloggername")
    val bloggername: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("postdate")
    val postdate: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("originallink")
    val originallink: String = ""
)

fun ContentResponse.mapToContentData(): Content =
    Content(
        query = query,
        contentItems = contentResponseItems.map {
            ContentItem(
                image = it.image,
                actor = it.actor,
                description = it.description,
                title = it.title,
                link = it.link
            )
        }
    )
