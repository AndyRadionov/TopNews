package io.github.andyradionov.googlenews.ui.search

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.andyradionov.googlenews.data.repositories.NewsRepository
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.reactivex.disposables.Disposable

/**
 * @author Andrey Radionov
 */
@InjectViewState
class SearchPresenter(private val newsInteractor: NewsInteractor) : MvpPresenter<SearchNewsView>() {

    private var subscription: Disposable? = null

    fun searchNews(query: String) {
        unsubscribe()

        newsInteractor.searchNews(query)
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