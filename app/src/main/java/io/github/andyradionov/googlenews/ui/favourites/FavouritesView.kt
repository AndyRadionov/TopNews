package io.github.andyradionov.googlenews.ui.favourites

import io.github.andyradionov.googlenews.ui.common.views.BaseNewsView

/**
 * @author Andrey Radionov
 */
interface FavouritesView: BaseNewsView {

    fun onFavouriteRemove(position: Int)
}