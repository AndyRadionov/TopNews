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

    }

    fun addToFavourites(article: Article) {

    }

    fun removeFromFavourites(articleId: Int) {

    }
}