package andyradionov.github.io.googlenews.ui.news

import andyradionov.github.io.googlenews.data.NewsRepository
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.Disposable

/**
 * @author Andrey Radionov
 */
@InjectViewState
class TopNewsPresenter (private val newsRepository: NewsRepository) :
        MvpPresenter<NewsView>() {

    private var subscription: Disposable? = null

    fun fetchNews() {
        unsubscribe()

        newsRepository.fetchNews()
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
        if (subscription?.isDisposed == true) {
            subscription?.dispose()
            subscription = null
        }
    }
}