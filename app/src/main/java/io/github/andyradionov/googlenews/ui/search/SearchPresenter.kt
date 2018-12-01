package io.github.andyradionov.googlenews.ui.search

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.utils.RxComposers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class SearchPresenter @Inject constructor(private val newsInteractor: NewsInteractor,
                                          private val rxComposers: RxComposers) : MvpPresenter<SearchNewsView>() {

    private var disposable: Disposable? = null

    fun searchNews(query: String) {
        unsubscribe()

        disposable = newsInteractor.searchNews(query)
                .compose(rxComposers.getObservableComposer())
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
        if (disposable?.isDisposed == true) {
            disposable?.dispose()
            disposable = null
        }
    }
}