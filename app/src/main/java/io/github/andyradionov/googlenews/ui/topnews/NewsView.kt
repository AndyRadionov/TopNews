package io.github.andyradionov.googlenews.ui.topnews

import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.data.entities.Article

/**
 * @author Andrey Radionov
 */

interface NewsView : MvpView {

    fun showNews(articles: List<Article>)
    fun showError()
    fun showLoading()
}
