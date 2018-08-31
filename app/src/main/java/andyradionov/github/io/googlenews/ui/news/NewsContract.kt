package andyradionov.github.io.googlenews.ui.news

import andyradionov.github.io.googlenews.data.entities.Article
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * @author Andrey Radionov
 */

interface NewsContract {

    interface Presenter : MvpPresenter<View> {
        fun fetchNews(query: String)
    }

    interface View : MvpView {
        fun showNews(articles: List<Article>)
        fun showError()
        fun showLoading()
    }
}