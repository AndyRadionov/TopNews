package io.github.andyradionov.topnews.ui.headlines

import com.arellomobile.mvp.InjectViewState
import io.github.andyradionov.topnews.interactors.NewsInteractor
import io.github.andyradionov.topnews.ui.common.BaseNewsView
import io.github.andyradionov.topnews.ui.common.BasePresenter
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class HeadlinesPresenter @Inject constructor(
        private val newsInteractor: NewsInteractor
) : BasePresenter<BaseNewsView>() {

    fun fetchNewsForHeadline(headline: String) {
        if (checkNotConnected()) {
            viewState.showError()
            return
        }
        disposable = newsInteractor.fetchNewsForHeadline(headline)
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
