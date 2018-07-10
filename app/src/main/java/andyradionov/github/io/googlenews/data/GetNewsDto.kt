package andyradionov.github.io.googlenews.data

import com.google.gson.annotations.SerializedName

/**
 * @author Andrey Radionov
 */
class GetNewsDto {
    var totalResults: Int = 0
    var articles: List<Article> = emptyList()
    var status: String? = null

    override fun toString(): String {
        return "News{" +
                "totalResults = '" + totalResults + '\'' +
                ",articles = '" + articles + '\'' +
                ",status = '" + status + '\'' +
                "}"
    }
}