package io.github.andyradionov.topnews.ui.favourites

import com.arellomobile.mvp.InjectViewState
import io.github.andyradionov.topnews.interactors.NewsInteractor
import io.github.andyradionov.topnews.ui.common.BaseNewsView
import io.github.andyradionov.topnews.ui.common.BasePresenter
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class FavouritesPresenter @Inject constructor(
        private val newsInteractor: NewsInteractor
) : BasePresenter<BaseNewsView>() {

    fun loadFavourites() {
        dispose()
        disposable = newsInteractor.getFavourites()
                .compose(rxComposers.getFlowableComposer())
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
