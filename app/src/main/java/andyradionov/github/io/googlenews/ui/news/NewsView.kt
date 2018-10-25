package andyradionov.github.io.googlenews.ui.news

import andyradionov.github.io.googlenews.data.entities.Article
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

/**
 * @author Andrey Radionov
 */

interface NewsView : MvpView {

    fun showNews(articles: List<Article>)
    fun showError()
    fun showLoading()
}