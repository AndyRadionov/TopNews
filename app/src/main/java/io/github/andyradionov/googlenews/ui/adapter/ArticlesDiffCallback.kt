package io.github.andyradionov.googlenews.ui.adapter

import android.support.v7.util.DiffUtil
import io.github.andyradionov.googlenews.data.entities.Article

/**
 * @author Andrey Radionov
 */
class ArticlesDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(article1: Article, article2: Article) =
            article1.title == article2.title &&
                    article1.publishedAt == article2.publishedAt

    override fun areContentsTheSame(article1: Article, article2: Article) =
            article1 == article2
}
