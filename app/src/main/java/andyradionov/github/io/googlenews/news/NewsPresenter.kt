package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.Article
import andyradionov.github.io.googlenews.data.NewsApi
import andyradionov.github.io.googlenews.data.NewsStore
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class NewsPresenter : NewsContract.Presenter {

    @Inject lateinit var mNewsStore: NewsStore
    lateinit var mView: NewsContract.View

    init {
        App.sAppComponent.inject(this)
    }

    override fun getTopNews() {
        mNewsStore.getTopNews(this)
    }

    override fun searchNews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNews(articles: List<Article>) {
        mView.showNews(articles)
    }

    override fun showError() {
        mView.showError()
    }

    override fun attachView(view: NewsContract.View) {
        mView = view
    }

    override fun detachView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}