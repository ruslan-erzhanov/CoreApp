package com.erzhanov.coreapp.data.reddits.model

import com.erzhanov.coreapp.data.storage.model.EntityReddit

data class Reddit(
    val id: String,
    val saved: Boolean,
    val subreddit: String,
    val title: String,
    val thumbnail: String,
    val author: String,
    val url: String,
    val num_comments: Int,
    val created: Long,
    val ups: Int,
)

val Reddit.asEntity: EntityReddit
    get() = EntityReddit(
        id = id,
        subreddit = subreddit,
        title = title,
        thumbnail = thumbnail,
        author = author,
        url = url,
        num_comments = num_comments,
        created = created,
        ups = ups
    )

val EntityReddit.asDomain: Reddit
    get() = Reddit(
        id = id,
        subreddit = subreddit,
        title = title,
        thumbnail = thumbnail,
        author = author,
        url = url,
        num_comments = num_comments,
        created = created,
        ups = ups,
        saved = true
    )

data class RedditsPage(
    val items: List<Reddit>,
    val token: String
)