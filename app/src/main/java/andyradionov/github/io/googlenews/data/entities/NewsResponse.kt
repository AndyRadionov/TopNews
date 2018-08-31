package andyradionov.github.io.googlenews.data.entities

/**
 * @author Andrey Radionov
 */
data class NewsResponse (var totalResults: Int = 0,
                         var articles: List<Article> = emptyList(),
                         var status: String? = null)