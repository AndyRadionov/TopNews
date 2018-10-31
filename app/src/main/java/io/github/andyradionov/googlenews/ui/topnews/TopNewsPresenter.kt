package io.github.andyradionov.googlenews.ui.topnews

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.andyradionov.googlenews.data.NewsRepository
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