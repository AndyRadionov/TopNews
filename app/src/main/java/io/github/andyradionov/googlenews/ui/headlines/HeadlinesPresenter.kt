package io.github.andyradionov.googlenews.ui.headlines

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.andyradionov.googlenews.data.NewsRepository
import io.github.andyradionov.googlenews.ui.common.BaseNewsView
import io.reactivex.disposables.Disposable

/**
 * @author Andrey Radionov
 */
@InjectViewState
class HeadlinesPresenter(private val newsRepository: NewsRepository) :
        MvpPresenter<BaseNewsView>() {

    private var subscription: Disposable? = null

    fun fetchNewsForHeadline(headline: String) {
        unsubscribe()

        newsRepository.fetchNewsForHeadline(headline)
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