package io.github.andyradionov.googlenews.ui.topnews

import com.arellomobile.mvp.InjectViewState
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.common.BaseNewsView
import io.github.andyradionov.googlenews.ui.common.BasePresenter
import io.github.andyradionov.googlenews.utils.RxComposers
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class TopNewsPresenter @Inject constructor(
        private val newsInteractor: NewsInteractor,
        private val rxSchedulers: RxComposers) :
        BasePresenter<BaseNewsView>() {

    fun fetchNews() {
        if (checkNotConnected()) return
        disposable = newsInteractor.fetchNews()
                .compose(rxSchedulers.getObservableComposer())
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
