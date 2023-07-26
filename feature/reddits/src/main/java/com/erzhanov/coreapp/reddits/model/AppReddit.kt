package com.erzhanov.coreapp.reddits.model

import com.erzhanov.coreapp.core.base.ui.model.ListItem
import com.erzhanov.coreapp.data.reddits.model.Reddit

data class AppReddit(
    override val id: String,
    val saved: Boolean,
    val subreddit: String,
    val title: String,
    val thumbnail: String,
    val author: String,
    val url: String,
    val num_comments: Int,
    val created: Long,
    val ups: Int,
): ListItem

val Reddit.asApp: AppReddit
    get() = AppReddit(
        id = id,
        subreddit = subreddit,
        title = title,
        thumbnail = thumbnail,
        author = author,
        url = url,
        num_comments = num_comments,
        created = created,
        ups = ups,
        saved = saved
    )

val AppReddit.asDomain: Reddit
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
        saved = saved
    )