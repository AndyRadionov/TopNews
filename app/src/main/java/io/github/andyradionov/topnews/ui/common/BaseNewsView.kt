package io.github.andyradionov.topnews.ui.common

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import io.github.andyradionov.topnews.data.entities.Article

/**
 * @author Andrey Radionov
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseNewsView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showError()
//    @StateStrategyType(SkipStrategy::class)
//    fun showSuccess(msgId: Int)
    fun showNews(articles: List<Article>)
    fun showLoading()
}