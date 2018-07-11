package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.data.Article

/**
 * @author Andrey Radionov
 */
interface NewsContract {

    interface Presenter {
        fun getTopNews()
        fun searchNews(query: String)
        fun showNews(articles: List<Article>)
        fun showError()
        fun attachView(view: NewsContract.View)
        fun detachView()
    }

    interface View {
        fun showNews(articles: List<Article>)
        fun showError()
    }
}