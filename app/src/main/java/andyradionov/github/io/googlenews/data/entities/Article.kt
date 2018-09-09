package andyradionov.github.io.googlenews.data.entities

import java.util.*

data class Article(var publishedAt: Date? = null,
              var author: Any? = null,
              var urlToImage: String? = null,
              var description: String? = null,
              var title: String? = null,
              var url: String? = null)
