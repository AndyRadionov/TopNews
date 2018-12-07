package io.github.andyradionov.googlenews.ui.favourites

import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.data.entities.Article

/**
 * @author Andrey Radionov
 */
interface FavouritesView: MvpView {

    fun showFavourites(articles: List<Article>)
}