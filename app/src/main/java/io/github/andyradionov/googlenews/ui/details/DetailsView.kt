package io.github.andyradionov.googlenews.ui.details

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.github.andyradionov.googlenews.ui.common.views.BaseNewsView

/**
 * @author Andrey Radionov
 */
interface DetailsView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun onFavouriteRemoved()
}