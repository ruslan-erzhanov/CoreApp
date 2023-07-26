package com.erzhanov.coreapp.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavedReddit")
data class EntityReddit(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "subreddit") val subreddit: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "num_comments") val num_comments: Int,
    @ColumnInfo(name = "created") val created: Long,
    @ColumnInfo(name = "ups") val ups: Int,
)