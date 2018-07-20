package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.Article
import andyradionov.github.io.googlenews.data.NewsCallback
import andyradionov.github.io.googlenews.data.NewsStore
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class NewsPresenter(private val newsStore: NewsStore) :
        NewsContract.Presenter, NewsCallback {


    private var mView: NewsContract.View? = null

    override fun getTopNews() {
        newsStore.fetchNews(callback = this)
    }

    override fun searchNews(query: String) {
        newsStore.fetchNews(query, this)
    }

    override fun attachView(view: NewsContract.View) {
        mView = view
    }

    override fun detachView() {
        newsStore.unsubscribe()
        mView = null
    }

    override fun onSuccessLoading(articles: List<Article>) {
        mView?.showNews(articles)
    }

    override fun onErrorLoading() {
        mView?.showError()
    }
}