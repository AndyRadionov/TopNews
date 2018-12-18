package io.github.andyradionov.googlenews.ui.common.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.github.andyradionov.googlenews.data.entities.Article

/**
 * @author Andrey Radionov
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseNewsView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showError()
    fun showNews(articles: List<Article>)
    fun showLoading()
}