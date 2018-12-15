package io.github.andyradionov.googlenews.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "articles")
data class Article(@PrimaryKey(autoGenerate = true)
                   var articleId: Int,
                   val publishedAt: Date,
                   val author: String,
                   val urlToImage: String,
                   val description: String,
                   val title: String,
                   val url: String,
                   var isFavourite: Boolean = false)
