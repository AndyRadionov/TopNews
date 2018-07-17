package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.Article
import andyradionov.github.io.googlenews.data.NewsStore
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class NewsPresenter : NewsContract.Presenter {

    @Inject lateinit var mNewsStore: NewsStore
    private var mView: NewsContract.View? = null

    init {
        App.appComponent.inject(this)
    }

    override fun getTopNews() {
        mNewsStore.fetchNews(presenter = this)
    }

    override fun searchNews(query: String) {
        mNewsStore.fetchNews(query, this)
    }

    override fun showNews(articles: List<Article>) {
        mView?.showNews(articles)
    }

    override fun showError() {
        mView?.showError()
    }

    override fun attachView(view: NewsContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}