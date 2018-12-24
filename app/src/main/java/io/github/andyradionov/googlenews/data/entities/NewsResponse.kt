package io.github.andyradionov.googlenews.data.entities

import com.google.gson.annotations.SerializedName

/**
 * @author Andrey Radionov
 */
data class NewsResponse(
        @SerializedName("totalResults") val totalResults: Int = 0,
        @SerializedName("articles") val articles: List<Article> = emptyList(),
        @SerializedName("status") val status: String? = null)