package io.github.andyradionov.googlenews.ui.favourites

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.common.BaseNewsView
import io.github.andyradionov.googlenews.utils.RxComposers
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class FavouritesPresenter @Inject constructor(
        private val newsInteractor: NewsInteractor,
        private val rxSchedulers: RxComposers)
    : MvpPresenter<BaseNewsView>() {

    fun loadFavourites() {
        newsInteractor.getFavourites()
                .compose(rxSchedulers.getFlowableComposer())
                .subscribe(/*todo*/)
    }

    fun addToFavourites(article: Article) {
        newsInteractor.addToFavourites(article)
                .compose(rxSchedulers.getCompletableComposer())
                .subscribe(/*todo*/)
    }

    fun removeFromFavourites(articleId: Int) {
        newsInteractor.removeFromFavourites(articleId)
                .compose(rxSchedulers.getCompletableComposer())
                .subscribe(/*todo*/)
    }
}
