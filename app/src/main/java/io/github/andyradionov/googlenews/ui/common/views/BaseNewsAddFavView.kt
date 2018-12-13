package io.github.andyradionov.googlenews.ui.common.views

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Andrey Radionov
 */
interface BaseNewsAddFavView: BaseNewsView {

    @StateStrategyType(SkipStrategy::class)
    fun onFavouriteAdded()
}