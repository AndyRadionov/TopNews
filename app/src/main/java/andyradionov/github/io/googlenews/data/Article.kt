package andyradionov.github.io.googlenews.data

import java.util.*

class Article {
    var publishedAt: Date? = null
    var author: Any? = null
    var urlToImage: String? = null
    var description: String? = null
    var title: String? = null
    var url: String? = null

    override fun toString(): String {
        return "ArticlesItem{" +
                "publishedAt = '" + publishedAt + '\'' +
                ",author = '" + author + '\'' +
                ",urlToImage = '" + urlToImage + '\'' +
                ",description = '" + description + '\'' +
                ",title = '" + title + '\'' +
                ",url = '" + url + '\'' +
                "}"
    }
}
