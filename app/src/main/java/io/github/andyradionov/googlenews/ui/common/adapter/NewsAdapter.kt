package io.github.andyradionov.googlenews.ui.common.adapter

import com.hannesdorfmann.adapterdelegates3.AsyncListDifferDelegationAdapter
import io.github.andyradionov.googlenews.data.entities.Article

/**
 * @author Andrey Radionov
 */
class NewsAdapter(clickListener: NewsAdapterDelegate.OnArticleClickListener) :
        AsyncListDifferDelegationAdapter<Article>(ArticlesDiffCallback()) {

    init {
        delegatesManager.addDelegate(NewsAdapterDelegate(clickListener))
    }
}
