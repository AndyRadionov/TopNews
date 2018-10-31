package andyradionov.github.io.googlenews.ui.topnews

import andyradionov.github.io.googlenews.data.entities.Article
import com.arellomobile.mvp.MvpView

/**
 * @author Andrey Radionov
 */

interface NewsView : MvpView {

    fun showNews(articles: List<Article>)
    fun showError()
    fun showLoading()
}