package io.github.andyradionov.googlenews.ui.search

import com.arellomobile.mvp.InjectViewState
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.common.BaseNewsView
import io.github.andyradionov.googlenews.ui.common.BasePresenter
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class SearchPresenter @Inject constructor(
        private val newsInteractor: NewsInteractor
) : BasePresenter<BaseNewsView>() {

    fun searchNews(query: String) {
        if (checkNotConnected()) return
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
}
