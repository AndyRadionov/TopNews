package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.data.Article
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * @author Andrey Radionov
 */

interface NewsContract {

    interface Presenter : MvpPresenter<View> {
        fun getTopNews()
        fun searchNews(query: String)
    }

    interface View : MvpView {
        fun showNews(articles: List<Article>)
        fun showError()
        fun showLoading()
    }
}