package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.Article
import andyradionov.github.io.googlenews.data.NewsCallback
import andyradionov.github.io.googlenews.data.NewsStore
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class NewsPresenter : NewsContract.Presenter, NewsCallback {

    @Inject lateinit var mNewsStore: NewsStore
    private var mView: NewsContract.View? = null

    init {
        App.appComponent.inject(this)
    }

    override fun getTopNews() {
        if (mNewsStore.isCached("")) {
            mView?.showNews(mNewsStore.getCache())
            return
        }
        mNewsStore.fetchNews(callback = this)
    }

    override fun searchNews(query: String) {
        if (mNewsStore.isCached(query)) {
            mView?.showNews(mNewsStore.getCache())
            return
        }
        mNewsStore.fetchNews(query, this)
    }

    override fun attachView(view: NewsContract.View) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    override fun onSuccessLoading(articles: List<Article>) {
        mView?.showNews(articles)
    }

    override fun onErrorLoading() {
        mView?.showError()
    }
}