package io.github.andyradionov.topnews.ui.topnews

import com.arellomobile.mvp.InjectViewState
import io.github.andyradionov.topnews.interactors.NewsInteractor
import io.github.andyradionov.topnews.ui.common.BaseNewsView
import io.github.andyradionov.topnews.ui.common.BasePresenter
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class TopNewsPresenter @Inject constructor(
        private val newsInteractor: NewsInteractor
) : BasePresenter<BaseNewsView>() {

    fun fetchNews() {
        if (checkNotConnected()) return
        disposable = newsInteractor.fetchNews()
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
