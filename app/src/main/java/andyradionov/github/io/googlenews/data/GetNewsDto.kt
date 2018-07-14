package andyradionov.github.io.googlenews.data

import com.google.gson.annotations.SerializedName

/**
 * @author Andrey Radionov
 */
data class GetNewsDto (var totalResults: Int = 0,
                  var articles: List<Article> = emptyList(),
                  var status: String? = null)