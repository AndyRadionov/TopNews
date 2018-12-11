package io.github.andyradionov.googlenews.ui.favourites

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
class FavouritesPresenter @Inject constructor(
        private val newsInteractor: NewsInteractor,
        private val rxSchedulers: RxComposers)
    : MvpPresenter<FavouritesView>() {

    private var disposable: Disposable? = null

    fun loadFavourites() {
        disposable?.dispose()
        disposable = newsInteractor.getFavourites()
                .compose(rxSchedulers.getFlowableComposer())
                .subscribe({articles ->
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
        disposable?.dispose()
        disposable = newsInteractor.removeFromFavourites(articleId)
                .compose(rxSchedulers.getCompletableComposer())
                .subscribe {
                    viewState.onFavouriteRemove(position)
                }
    }
}
