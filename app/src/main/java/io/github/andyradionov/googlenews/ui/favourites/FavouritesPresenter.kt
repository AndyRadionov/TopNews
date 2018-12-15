package io.github.andyradionov.googlenews.ui.favourites

import com.arellomobile.mvp.InjectViewState
import io.github.andyradionov.googlenews.model.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.common.BasePresenter
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class FavouritesPresenter @Inject constructor(
        private val newsInteractor: NewsInteractor) :
        BasePresenter<FavouritesView>() {

    fun loadFavourites() {
        if (checkNotConnected()) return
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

    fun removeFromFavourites(articleId: Int, position: Int) {
        if (checkNotConnected()) return
        disposable = newsInteractor.removeFromFavourites(articleId)
                .compose(rxComposers.getCompletableComposer())
                .subscribe {
                    viewState.onFavouriteRemove(position)
                }
    }
}
