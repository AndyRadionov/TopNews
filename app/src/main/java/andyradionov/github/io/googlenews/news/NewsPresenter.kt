package andyradionov.github.io.googlenews.news

import andyradionov.github.io.googlenews.data.Article
import andyradionov.github.io.googlenews.data.NewsCallback
import andyradionov.github.io.googlenews.data.NewsRepository
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

/**
 * @author Andrey Radionov
 */
class NewsPresenter(private val newsRepository: NewsRepository) :
        MvpBasePresenter<NewsContract.View>(),
        NewsCallback,
        NewsContract.Presenter{

    override fun getTopNews() {
        newsRepository.fetchNews(callback = this)
    }

    override fun searchNews(query: String) {
        newsRepository.fetchNews(query, this)
    }

    override fun onSuccessLoading(articles: List<Article>) {
        ifViewAttached { it.showNews(articles) }
    }

    override fun onErrorLoading() {
        ifViewAttached { it.showError() }
    }

    override fun destroy() {
        super.destroy()
        newsRepository.unsubscribe()
    }
}