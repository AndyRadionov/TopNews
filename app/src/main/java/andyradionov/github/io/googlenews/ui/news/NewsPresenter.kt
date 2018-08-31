package andyradionov.github.io.googlenews.ui.news

import andyradionov.github.io.googlenews.data.NewsRepository
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.disposables.Disposable

/**
 * @author Andrey Radionov
 */
class NewsPresenter(private val newsRepository: NewsRepository) :
        MvpBasePresenter<NewsContract.View>(),
        NewsContract.Presenter {

    private var mSubscription: Disposable? = null

    override fun fetchNews(query: String) {
        unsubscribe()

        newsRepository.fetchNews(query)
                .subscribe({ articles ->
                    if (articles.isEmpty()) {
                        ifViewAttached { it.showError() }
                    } else {
                        ifViewAttached { it.showNews(articles) }

                    }
                }, {
                    ifViewAttached { it.showError() }
                })
    }

    override fun destroy() {
        super.destroy()
        unsubscribe()
    }

    fun unsubscribe() {
        if (mSubscription?.isDisposed == true) {
            mSubscription?.dispose()
            mSubscription = null
        }
    }
}