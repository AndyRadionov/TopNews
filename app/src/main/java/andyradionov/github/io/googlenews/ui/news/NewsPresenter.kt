package andyradionov.github.io.googlenews.ui.news

import andyradionov.github.io.googlenews.data.NewsRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.Disposable

/**
 * @author Andrey Radionov
 */
@InjectViewState
class NewsPresenter (private val newsRepository: NewsRepository) :
        MvpPresenter<NewsView>() {

    private var mSubscription: Disposable? = null

    fun fetchNews(query: String) {
        unsubscribe()

        newsRepository.fetchNews(query)
                .subscribe({ articles ->
                    if (articles.isEmpty()) {
                        viewState.showError()
                    } else {
                        viewState.showNews(articles)

                    }
                }, {
                    viewState.showError()
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        unsubscribe()
    }

    fun unsubscribe() {
        if (mSubscription?.isDisposed == true) {
            mSubscription?.dispose()
            mSubscription = null
        }
    }
}