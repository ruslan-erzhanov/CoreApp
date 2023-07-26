package com.erzhanov.coreapp.data.reddits.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RootList(
    @SerialName("data") val data: ApiRedditPage,
)

@Serializable
data class ApiRedditPage(
    @SerialName("children") val children: List<ApiRedditChildren>,
    @SerialName("after") val after: String? = null,
)

@Serializable
data class ApiRedditChildren(
    @SerialName("data") val data: ApiReddit,
)

@Serializable
data class ApiReddit(
    @SerialName("id") val id: String,
    @SerialName("subreddit") val subreddit: String?,
    @SerialName("title") val title: String,
    @SerialName("thumbnail") val thumbnail: String?,
    @SerialName("author") val author: String,
    @SerialName("url") val url: String,
    @SerialName("num_comments") val num_comments: Int,
    @SerialName("created") val created: Double,
    @SerialName("ups") val ups: Int?,
)

fun ApiReddit.toDomainModel(saved: Boolean = false) = Reddit(
    id = id,
    subreddit = subreddit.orEmpty(),
    title = title,
    thumbnail = thumbnail.orEmpty(),
    author = author,
    url = url,
    num_comments = num_comments,
    created = created.toLong(),
    ups = ups ?: 0,
    saved = saved,
)