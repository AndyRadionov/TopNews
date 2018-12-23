package io.github.andyradionov.googlenews.ui.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.github.andyradionov.googlenews.data.entities.Article

/**
 * @author Andrey Radionov
 */
@StateStrategyType(SkipStrategy::class)
interface MainView: MvpView {

    fun initBottomTab()

    fun showBottomSheet(article: Article)

    fun showMessage(msg: String)

    fun showNotConnected()
}
